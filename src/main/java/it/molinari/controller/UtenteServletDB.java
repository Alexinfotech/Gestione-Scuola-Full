package it.molinari.controller;

import it.molinari.model.UtenteDTO;
import it.molinari.connessione.GestioneUtenti;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/UtenteServletDB")
public class UtenteServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestioneUtenti gestioneUtenti = new GestioneUtenti();

	public UtenteServletDB() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "create":
				request.setAttribute("utente", new UtenteDTO());
				request.getRequestDispatcher("views/utente/utente.jsp").forward(request, response);
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
				List<UtenteDTO> listaUtenti = gestioneUtenti.recuperaUtenti();
				request.setAttribute("listaUtenti", listaUtenti);
				request.getRequestDispatcher("views/utente/listaUtenti.jsp").forward(request, response);
				break;
			case "dettaglio":
				String codiceFiscaleDettaglio = request.getParameter("codiceFiscale");
				UtenteDTO utenteDettaglio = gestioneUtenti.getUtente(codiceFiscaleDettaglio);
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
				UtenteDTO utente = gestioneUtenti.getUtente(codiceFiscale);
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
				gestioneUtenti.deleteUtente(codiceFiscaleToDelete);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codiceFiscale = request.getParameter("codiceFiscale");
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                UtenteDTO utente = creaUtenteDTO(request);
                // Verifica se l'utente esiste già
                if (gestioneUtenti.getUtente(codiceFiscale) == null) {
                    // Se l'utente non esiste, procedi con la creazione
                    gestioneUtenti.inserisciUtente(utente);
                    response.sendRedirect("UtenteServletDB?action=list");
                } else {
                    // Se l'utente esiste già, mostra un messaggio di errore
                    request.setAttribute("errore", "Codice fiscale già esistente. Inserisci un codice fiscale diverso.");
                    request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                }
            } else if ("update".equals(action)) {
                doPut(request, response); // Chiamata diretta a doPut per l'aggiornamento
            } else {
                // Gestione azioni non supportate
                request.setAttribute("errore", "Azione non supportata.");
                request.getRequestDispatcher("views/errore.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
        }
    }

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Prendi il codice fiscale dall'URL o dal corpo della richiesta, a seconda di
		// come è strutturata la tua richiesta PUT.
		String codiceFiscale = req.getParameter("codiceFiscale");
		try {
			// Crea un utente aggiornato basato sui parametri della richiesta
			UtenteDTO utenteAggiornato = creaUtenteDTO(req);

			// Controlla se l'utente esiste già nel database
			UtenteDTO utenteEsistente = gestioneUtenti.getUtente(codiceFiscale);
			if (utenteEsistente != null) {
				// Aggiorna l'utente nel database
				gestioneUtenti.updateUtente(utenteAggiornato);
				resp.sendRedirect("UtenteServletDB?action=list");
			} else {
				// Imposta un messaggio di errore se l'utente non è trovato
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
		String codiceFiscale = request.getParameter("codiceFiscale");
		try {
			gestioneUtenti.deleteUtente(codiceFiscale);
			response.sendRedirect("UtenteServletDB?action=list");
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore durante l'eliminazione dell'utente");
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		}
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
