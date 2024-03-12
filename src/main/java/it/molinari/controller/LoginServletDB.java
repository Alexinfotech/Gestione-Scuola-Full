package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;

import it.molinari.DAO.LoginDAO;
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
            // Login di un utente esistente
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                String role = gestionelogin.validateUser(email, password);
                if (role != null) {
                    // Login riuscito, memorizza l'email e il ruolo in sessione
                    request.getSession().setAttribute("email", email);
                    request.getSession().setAttribute("role", role);
                    response.sendRedirect("views/welcome.jsp");
                } else {
                    // Login fallito, reindirizza alla pagina di login con un messaggio di errore
                    request.setAttribute("errore", "Email o password errata!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errore", "Errore nel database durante il login.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
