package it.molinari.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import it.molinari.model.UtenteDTO;
import it.molinari.DAO.LoginDAO;
import it.molinari.model.LoginDTO;
import it.molinari.service.Ruolo;
import it.molinari.service.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UtenteServletDB")
public class UtenteServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteService gestioneUtenti = new UtenteService();

	public UtenteServletDB() {
		super();
	}

	private LoginDTO recuperLoginDaIdUtente(String idUtente) throws SQLException {
		// Conversione dell'ID utente da String a int.
		int userId = Integer.parseInt(idUtente);

		// Utilizzo del LoginDAO per recuperare l'oggetto LoginDTO corrispondente.
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.findById(userId);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // Ottenere il ruolo dell'utente dalla sessione o da altri meccanismi
													// di autenticazione

		String action = request.getParameter("action");
		try {
			switch (action) {
			case "create":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/utente.jsp").forward(request, response);
				break;
			case "createModulo":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/utenteModulo.jsp").forward(request, response);
				break;
			case "createProva":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/ProvaCF.jsp").forward(request, response);
				break;
			case "CreateCF":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/SoloCF.jsp").forward(request, response);
				break;
			case "FormSearch":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/Search.jsp").forward(request, response);
				break;
			case "list":
				List<UtenteDTO> listaUtenti = gestioneUtenti.recupera();
				request.setAttribute("listaUtenti", listaUtenti);
				request.getRequestDispatcher("views/utente/listaUtenti.jsp").forward(request, response);
				break;
			case "ListMagaz":
				List<UtenteDTO> listaMagazzinieri = gestioneUtenti.recuperaMagazzinieri();

				request.setAttribute("listaMagazzinieri", listaMagazzinieri);
				request.getRequestDispatcher("views/utente/listaMagazzinieri.jsp").forward(request, response);
				break;
			case "dettaglio":
				String codiceFiscaleDettaglio = request.getParameter("codiceFiscale");
				UtenteDTO utenteDettaglio = gestioneUtenti.get(codiceFiscaleDettaglio);
				if (utenteDettaglio != null) {
					request.setAttribute("utente", utenteDettaglio);
					request.getRequestDispatcher("/views/utente/modificaUtente.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage",
							"Utente non trovato per il codice fiscale: " + codiceFiscaleDettaglio);
					request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
				}
				break;

			case "search":
				String codiceFiscale = request.getParameter("codiceFiscale");
				UtenteDTO utente = gestioneUtenti.get(codiceFiscale);
				if (utente != null) {
					request.setAttribute("utente", utente);
					request.getRequestDispatcher("views/utente/modificaUtente.jsp").forward(request, response);
				} else {
					request.setAttribute("errore", "Utente non trovato");
					request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				}
				break;
			case "delete":
				String codiceFiscaleToDelete = request.getParameter("codiceFiscale");
				gestioneUtenti.delete(codiceFiscaleToDelete);
				response.sendRedirect("UtenteServletDB?action=list");
				break;
			default:
				request.setAttribute("errore", "Azione non riconosciuta.");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				break;
			}
		} catch (SQLException | ClassNotFoundException e) {
			request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceFiscale = request.getParameter("codiceFiscale");
		String idUtente = request.getParameter("idUtente");
		String action = request.getParameter("action");

		try {
			if (idUtente != null && !idUtente.isEmpty()) {
				// L'ID utente è stato recuperato correttamente
				if ("create".equals(action)) {
					// Recupera l'oggetto LoginDTO associato all'idUtente
					LoginDTO login = recuperLoginDaIdUtente(idUtente);
					if (login != null) {
						UtenteDTO utente = creaUtenteDTO(request);
						// Verifica che i parametri utente siano validi
						if (utente != null) {
							// Procedi con l'inserimento
							// Assumi che 'utente' sia un oggetto UtenteDTO popolato con i dati del form
							// e che 'idUtente' sia l'ID dell'utente come stringa
							gestioneUtenti.inserisci(utente, idUtente);

							response.sendRedirect("ProdottoServlet?action=list");

						} else {
							// Messaggio di errore se i parametri utente non sono validi
							request.setAttribute("errore",
									"Parametri utente non validi. Impossibile inserire l'utente.");
							request.getRequestDispatcher("views/errore.jsp").forward(request, response);
						}
					} else {
						// Messaggio di errore se l'oggetto LoginDTO non è stato trovato per l'idUtente
						request.setAttribute("errore", "Nessun utente trovato per l'ID specificato.");
						request.getRequestDispatcher("views/errore.jsp").forward(request, response);
					}
				} else if ("update".equals(action)) {
					doPut(request, response); // Chiamata diretta a doPut per l'aggiornamento
				} else {
					// Messaggio di errore se l'azione non è supportata
					request.setAttribute("errore", "Azione non supportata.");
					request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				}
			} else {
				// Messaggio di errore se l'ID utente non è stato recuperato correttamente
				request.setAttribute("errore", "ID utente non valido.");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// Messaggio di errore se si verifica un'eccezione durante l'accesso al database
			request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codiceFiscale = req.getParameter("codiceFiscale");

		try {
			UtenteDTO utenteAggiornato = creaUtenteDTO(req);
			UtenteDTO utenteEsistente = gestioneUtenti.get(codiceFiscale);
			if (utenteEsistente != null) {
				gestioneUtenti.update(utenteAggiornato);
				resp.sendRedirect("UtenteServletDB?action=list");
			} else {
				req.setAttribute("errore", "Utente non trovato per il codice fiscale: " + codiceFiscale);
				req.getRequestDispatcher("views/errore.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Stampa i dettagli dell'errore nel log del server
			req.setAttribute("errorMessage", "Errore durante l'aggiornamento dell'utente: " + e.getMessage());
			req.getRequestDispatcher("views/errore.jsp").forward(req, resp);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruolo = (String) request.getSession().getAttribute("ruolo");
		if (!Ruolo.AMMINISTRATORE.equals(ruolo)) {
			request.setAttribute("errorMessage", "Accesso consentito solo agli amministratori.");
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
			return; // Interrompi l'esecuzione della servlet
		}
		String codiceFiscale = request.getParameter("codiceFiscale");
		try {
			gestioneUtenti.delete(codiceFiscale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("UtenteServletDB?action=list");
	}

	private UtenteDTO creaUtenteDTO(HttpServletRequest req) {
		UtenteDTO utente = new UtenteDTO();
		utente.setCodiceFiscale(req.getParameter("codiceFiscale"));
		utente.setNome(req.getParameter("nome"));
		utente.setCognome(req.getParameter("cognome"));
		utente.setEmail(req.getParameter("email"));

		String dataNascitaStr = req.getParameter("dataNascita");
		Date dataNascita = null;
		if (dataNascitaStr != null && !dataNascitaStr.isEmpty()) {
			try {
				dataNascita = Date.valueOf(dataNascitaStr);
			} catch (IllegalArgumentException e) {
				// Qui Gestirò l'eccezione se necessario
			}
		}
		utente.setDataNascita(dataNascita);
		utente.setComuneDiNascita(req.getParameter("comuneDiNascita"));
		utente.setProvincia(req.getParameter("provincia"));
		utente.setComuneDiResidenza(req.getParameter("comuneDiResidenza"));
		utente.setVia(req.getParameter("via"));
		utente.setNumeroCivico(req.getParameter("numeroCivico"));
		utente.setCap(req.getParameter("cap"));

		return utente;
	}
}
