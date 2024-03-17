package it.molinari.controller;

import it.molinari.model.OrdineDTO;
import it.molinari.service.OrdineService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/OrdineServlet")
public class OrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdineService gestioneOrdine = new OrdineService();

    public OrdineServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "visualizzaOrdini":
                    visualizzaOrdini(request, response);
                    break;
                // Puoi aggiungere altri casi qui per differenti azioni
                default:
                    request.setAttribute("errore", "Azione non riconosciuta.");
                    request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
        }
    }

    private void visualizzaOrdini(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int idUtente = (int) request.getSession().getAttribute("idUtente"); // Assumi che l'ID utente sia memorizzato nella sessione
        Map<String, List<OrdineDTO>> ordiniPerData = gestioneOrdine.recuperaOrdini(idUtente);
        request.setAttribute("ordiniPerData", ordiniPerData);
        request.getRequestDispatcher("/views/ordine/listaOrdini.jsp").forward(request, response);
    }

    // Metodo doPost se necessario, ad esempio per la gestione dell'invio di form
}
