
package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;

import it.molinari.connessione.GestioneLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServletDB")
public class LoginServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestioneLogin gestionelogin = new GestioneLogin();

	public LoginServletDB() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");
	    
	    if ("register".equals(action)) {
	        String newEmail = request.getParameter("newEmail");
	        String newPassword = request.getParameter("newPassword"); // Considera di hashare la password
	        
	        try {
	            if (!gestionelogin.emailExists(newEmail)) {
	                gestionelogin.registerNewUser(newEmail, newPassword);
	                response.sendRedirect("index.jsp"); // Reindirizza alla pagina di login dopo la registrazione
	            } else {
	                request.setAttribute("errore", "Email già in uso!");
	                request.getRequestDispatcher("registrazione.jsp").forward(request, response);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Log dell'errore
	            request.setAttribute("errore", "Errore nel database durante la registrazione.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	    } else if ("login".equals(action)) {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        try {
	            if (gestionelogin.validateUser(email, password)) {
	                request.getSession().setAttribute("email", email); // Memorizza l'email in sessione
	                response.sendRedirect("views/welcome.jsp"); // Reindirizza alla pagina di benvenuto
	            } else {
	                request.setAttribute("errore", "Email o password errata!");
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Log dell'errore
	            request.setAttribute("errore", "Errore nel database durante il login.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	    }
	}
}

