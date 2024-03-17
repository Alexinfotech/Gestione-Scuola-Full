package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;

import it.molinari.service.CarrelloService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarrelloService gestioneCarrello = new CarrelloService();

	public CarrelloServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "aggiungi":
				int prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
				int quantita = Integer.parseInt(request.getParameter("quantita"));
				// Assumiamo che l'ID dell'utente (loginId) sia memorizzato in sessione
				int loginId = (Integer) request.getSession().getAttribute("loginId");
				gestioneCarrello.aggiungiAlCarrello(loginId, prodottoId, quantita);
				response.sendRedirect("CarrelloServlet?action=mostraCarrello");
				break;
			case "mostraCarrello":
				// Assumiamo che l'ID dell'utente (loginId) sia memorizzato in sessione
				Integer loginId = (Integer) request.getSession().getAttribute("loginId");
				if (loginId != null) {
					// Recupera il carrello dell'utente corrente
					request.setAttribute("listaCarrello", gestioneCarrello.recuperaCarrello(loginId));
					// Inoltra alla JSP per mostrare il carrello
					request.getRequestDispatcher("/views/carrello/listaCarrello.jsp").forward(request, response);
				} else {
					// Gestire il caso in cui non si abbia un loginId (utente non loggato o sessione
					// scaduta)
					request.setAttribute("errore", "Per accedere al carrello è necessario effettuare il login.");
					request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				}
				break;
			case "rimuovi":
				String idCarrello = request.getParameter("idCarrello");
				gestioneCarrello.rimuoviDalCarrello(idCarrello);
				response.sendRedirect("CarrelloServlet?action=mostraCarrello");
				break;
			default:
				request.setAttribute("errore", "Azione non riconosciuta.");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				break;
			}
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if (idStr != null && !idStr.isEmpty()) {
			try {
				int id = Integer.parseInt(idStr);
				// Assumi che CarrelloService abbia un metodo delete che accetti un ID per
				// rimuovere un prodotto dal carrello
				new CarrelloService().delete(id); // Sostituisci con il tuo servizio/carrello effettivo
				response.sendRedirect(request.getContextPath() + "/CarrelloServlet?action=list"); // Reindirizza
																									// l'utente alla
																									// lista aggiornata
			} catch (NumberFormatException e) {
				// Gestisci l'eccezione se l'ID non è valido (non un numero)
				request.setAttribute("errorMessage", "Formato ID non valido.");
				request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
			} catch (SQLException e) {
				// Gestisci eventuali errori SQL
				request.setAttribute("errorMessage", "Errore durante l'eliminazione del prodotto: " + e.getMessage());
				request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
			}
		} else {
			// Gestisci il caso in cui l'ID non sia fornito
			request.setAttribute("errorMessage", "ID prodotto non fornito.");
			request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
		}
	}
}
