package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;

import it.molinari.DAO.LoginDAO;
import it.molinari.model.LoginDTO;
import it.molinari.service.Ruolo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServletDB")
public class LoginServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO gestionelogin = new LoginDAO();

	public LoginServletDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("nuovoMagazziniere".equals(action)) {
			// Reindirizza alla pagina nuovoMagazziniere.jsp
			request.getRequestDispatcher("views/utente/NuovoMagazziniere.jsp").forward(request, response);

		} else {
			// Gestisci altri casi o reindirizzamenti qui se necessario
			// ...
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("register".equals(action)) {
			// Registrazione di un nuovo utente
			String newEmail = request.getParameter("newEmail");
			String newPassword = request.getParameter("newPassword");

			try {
				if (!gestionelogin.emailExists(newEmail)) {
					gestionelogin.registerNewUser(newEmail, newPassword);
					response.sendRedirect("index.jsp");
				} else {
					request.setAttribute("errore", "Email già in uso!");
					request.getRequestDispatcher("registrazione.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errore", "Errore nel database durante la registrazione.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
				LoginDTO user = gestionelogin.validateUser(email, password);
				if (user != null && user.getRuolo() != null) {
					// Controlla se l'anagrafica è completa
					boolean anagraficaCompleta = gestionelogin.isAnagraficaCompleta(user.getIdUtente());

					if (!anagraficaCompleta) {
						// Se l'anagrafica non è completa, reindirizza all'jsp per completarla
						request.getSession().setAttribute("ruolo", user.getRuolo());

						request.getSession().setAttribute("idUtente", user.getIdUtente()); // Potrebbe essere utile per
																							// il form di anagrafica
						response.sendRedirect("UtenteServletDB?action=createModulo");
					} else {
						// Anagrafica completa, procedi come prima
						request.getSession().setAttribute("idUtente", user.getIdUtente());
						request.getSession().setAttribute("email", user.getEmailLogin());
						request.getSession().setAttribute("ruolo", user.getRuolo());
						response.sendRedirect("views/welcome.jsp");
					}
				} else {
					request.setAttribute("errore", "Email o password errata!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errore", "Errore nel database durante il login.");
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
		} else if ("registraMagazziniere".equals(action)) {

			String newEmail = request.getParameter("newEmail");
			String newPassword = request.getParameter("newPassword");

			try {
				if (!gestionelogin.emailExists(newEmail)) {
					gestionelogin.registraMagazziniere(newEmail, newPassword);
					response.sendRedirect("views/welcome.jsp");
				} else {
					request.setAttribute("errore", "Email già in uso!");
					request.getRequestDispatcher("views/utente/NuovoMagazziniere.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errore", "Errore nel database durante la registrazione.");
				request.getRequestDispatcher("views/error.jsp").forward(request, response);
			}
		} else if ("logout".equals(action)) { // Aggiunta del codice per il logout
			request.getSession().invalidate(); // Invalida la sessione
			response.sendRedirect("index.jsp"); // Reindirizza alla pagina iniziale
		}
	}
}
