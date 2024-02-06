package it.molinari.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import it.molinari.model.UtenteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UtenteServlet")
public class UtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, UtenteDTO> mappaUtenti = new HashMap<>();
	private String apiToken;

	public UtenteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
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
			request.setAttribute("mappaUtenti", mappaUtenti);
			request.getRequestDispatcher("views/utente/listaUtenti.jsp").forward(request, response);
			break;
		case "dettaglio":
			String id = request.getParameter("id");
			if (id != null && mappaUtenti.containsKey(id)) {
				request.setAttribute("utente", mappaUtenti.get(id));
				request.getRequestDispatcher("views/utente/modificaUtente.jsp").forward(request, response);
			} else {
				request.setAttribute("errore", "Utente non trovato");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
			}
			break;
		case "search":
			String codiceFiscale = request.getParameter("codiceFiscale");
			if (codiceFiscale != null && mappaUtenti.containsKey(codiceFiscale)) {
				request.setAttribute("utente", mappaUtenti.get(codiceFiscale));
				request.getRequestDispatcher("views/utente/modificaUtente.jsp").forward(request, response);
			} else {
				request.setAttribute("errore", "Utente non esiste");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
			}

			break;

		case "delete":
			doDelete(request, response);
			break;
		default:
			System.out.println("Azione non riconosciuta.");
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codiceFiscale = request.getParameter("codiceFiscale");

		// Controllo se l'azione è una creazione di un nuovo utente
		if ("create".equals(request.getParameter("action"))) {
			if (mappaUtenti.containsKey(codiceFiscale)) {
				// Imposto un messaggio di errore test
				request.setAttribute("errore", "Codice fiscale già esistente. Inserisci un codice fiscale diverso.");

				// Reimposta i valori inseriti dall'utente
				request.setAttribute("utente", creaUtenteDTO(request));

				// Reindirizza alla pagina di creazione dell'utente
				request.getRequestDispatcher("views/utente/utente.jsp").forward(request, response);
				return; // Esco dalla funzione per evitare di eseguire ulteriori azioni
			}
		}

		// Crea un nuovoìo aggiorna un utente esistente
		UtenteDTO utente = creaUtenteDTO(request);
		mappaUtenti.put(codiceFiscale, utente);

		// Reindirizza alla lista degli utenti
		response.sendRedirect("UtenteServlet?action=list");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null && mappaUtenti.containsKey(id)) {
			mappaUtenti.remove(id);
			response.sendRedirect("UtenteServlet?action=list");
		} else {
			request.setAttribute("errore", "Utente non trovato");
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codiceFiscale = req.getParameter("codiceFiscale");
		// String nuovoCodiceFiscale = req.getParameter("codiceFiscale");

		try {
			UtenteDTO utenteAggiornato = creaUtenteDTO(req);

			if (!codiceFiscale.equals(codiceFiscale)) {
				// Non Rimuove il vecchio utente
				// mappaUtenti.remove(codiceFiscale);

				mappaUtenti.put(codiceFiscale, utenteAggiornato);
			} else {

				mappaUtenti.put(codiceFiscale, utenteAggiornato);
			}

			resp.sendRedirect("UtenteServlet?action=list");
		} catch (Exception e) {
			e.printStackTrace(); // Stampo i dettagli dell'errore nel log del server
			req.setAttribute("errorMessage", "Errore durante l'aggiornamento dell'utente: " + e.getMessage());
			req.getRequestDispatcher("views/errore.jsp").forward(req, resp);
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
