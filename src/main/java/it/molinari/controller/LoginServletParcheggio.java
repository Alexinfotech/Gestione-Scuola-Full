package it.molinari.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> userCredentials = new HashMap<>();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("register".equals(action)) {
			String newEmail = request.getParameter("newEmail");
			String newPassword = request.getParameter("newPassword");

			if (!userCredentials.containsKey(newEmail)) {
				userCredentials.put(newEmail, newPassword);
				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("errore", "Email già in uso!");
				request.getRequestDispatcher("registrazione.jsp").forward(request, response);
			}
		} else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (userCredentials.containsKey(email) && userCredentials.get(email).equals(password)) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("views/welcome.jsp").forward(request, response);
			} else {
				request.setAttribute("errore", "Email o password errata!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}
}
