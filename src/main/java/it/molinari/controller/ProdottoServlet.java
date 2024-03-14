package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import it.molinari.model.ProdottoDTO;
import it.molinari.service.ProdottoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProdottoServlet")
public class ProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottoService gestioneProdotto = new ProdottoService();

    public ProdottoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "create":
                    request.setAttribute("prodotto", new ProdottoDTO());
                    request.getRequestDispatcher("views/prodotto/prodotto.jsp").forward(request, response);
                    break;
                case "FormSearch":
                    request.setAttribute("prodotto", new ProdottoDTO());
                    request.getRequestDispatcher("views/prodotto/SearchProdotto.jsp").forward(request, response);
                    break;
                case "list":
                    List<ProdottoDTO> listaProdotto = gestioneProdotto.recupera();
                    request.setAttribute("listaProdotto", listaProdotto);
                    request.getRequestDispatcher("views/prodotto/listaProdotto2.jsp").forward(request, response);
                    break;

                case "dettaglio":
                    String idDettaglio = request.getParameter("id");
                    if (idDettaglio != null && !idDettaglio.isEmpty()) {
                        try {
                        	ProdottoDTO prodottoDettaglio = gestioneProdotto.get(idDettaglio);
                            if (prodottoDettaglio != null) {
                                request.setAttribute("prodotto", prodottoDettaglio);
                                request.getRequestDispatcher("/views/prodotto/modificaProdotto.jsp").forward(request, response);
                            } else {
                                request.setAttribute("errorMessage", "Prodotto non trovato per l'ID inserito: " + idDettaglio);
                                request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("errorMessage", "Formato ID non valido: " + idDettaglio);
                            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errorMessage", "ID prodotto non fornito.");
                        request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
                    }
                    break;
                case "acquista":
                    String prodottoIdStr = request.getParameter("prodottoId");
                    if (prodottoIdStr != null && !prodottoIdStr.trim().isEmpty()) {
                        try {
                            // Converti l'ID del prodotto da String a int
                            int prodottoId = Integer.parseInt(prodottoIdStr);
                            // Supponiamo che tu abbia un metodo get(String id) che restituisce un ProdottoDTO dato un ID come stringa
                            ProdottoDTO prodotto = gestioneProdotto.get(prodottoIdStr);
                            if (prodotto != null) {
                                gestioneProdotto.acquista(prodotto);
                                response.sendRedirect("ProdottoServlet?action=list");
                            } else {
                                // Gestisci il caso in cui il prodotto con quell'ID non esiste
                                request.setAttribute("errore", "Prodotto non trovato per l'ID: " + prodottoIdStr);
                                request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("errore", "Formato ID non valido: " + prodottoIdStr);
                            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                        } catch (SQLException e) {
                            request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
                            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errore", "ID del prodotto non fornito.");
                        request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                    }
                    break;



                case "search":
                    String nomeProdotto = request.getParameter("nomeProdotto");
                    ProdottoDTO prodotto = null;
                    try {
                        List<ProdottoDTO> listaProdotti = gestioneProdotto.recupera();
                        for (ProdottoDTO p : listaProdotti) {
                            if (p.getNomeProdotto().equals(nomeProdotto)) {
                                prodotto = p;
                                break;
                            }
                        }
                        if (prodotto != null) {
                            request.setAttribute("prodotto", prodotto);
                            request.getRequestDispatcher("views/utente/modificaProdotto.jsp").forward(request, response);
                        } else {
                            request.setAttribute("errore", "Prodotto non trovato");
                            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
                        request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                    }
                    break;
                case "delete":
                    String idToDelete = request.getParameter("id");
                    String ruoloUtente = (String) request.getSession().getAttribute("ruolo"); // Recupera il ruolo dall'oggetto sessione
                    gestioneProdotto.delete(idToDelete, ruoloUtente); // Passa l'ID e il ruolo al metodo delete
                    response.sendRedirect("ProdottoServlet?action=list");
                    break;

                /*case "delete":
                    String idToDelete = request.getParameter("id");
                    gestioneProdotto.delete(idToDelete);
                    response.sendRedirect("ProdottoServlet?action=list");
                    break;*/
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
/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("update".equals(action)) {
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr); // Converti l'ID in int
                    ProdottoDTO prodotto = creaProdottoDTO(request);
                    prodotto.setId(id); // Imposta l'ID sul DTO
                    gestioneProdotto.update(prodotto);
                    response.sendRedirect("ProdottoServlet?action=list");
                } else {
                    // Gestisci il caso in cui l'ID non sia fornito
                    request.setAttribute("errore", "ID del prodotto mancante.");
                    request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                }
            } else {
                // Gestione degli altri casi
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Formato ID non valido.");
            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
        }
    }
    
*/
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        // Ottieni il ruolo dalla sessione HTTP
        String ruoloUtente = (String) request.getSession().getAttribute("ruolo");

        try {
            // Assicurati che l'ID sia fornito e valido
            int idInt = Integer.parseInt(idStr);
            
            // Passa l'ID e il ruolo al metodo delete della service
            gestioneProdotto.delete(idInt, ruoloUtente);

            // Redireziona l'utente alla lista dei prodotti dopo l'eliminazione
            response.sendRedirect("ProdottoServlet?action=list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Formato ID non valido. Assicurati che l'ID sia un numero.");
            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
        } catch (SecurityException se) {
            // Gestione del caso in cui l'utente non ha il permesso di eliminare il prodotto
            request.setAttribute("errorMessage", se.getMessage());
            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Errore durante l'eliminazione del prodotto dal database.");
            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
        }
    }
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    try {
        if ("create".equals(action)) {
            ProdottoDTO prodotto = creaProdottoDTO(request);
            gestioneProdotto.create(prodotto);
            response.sendRedirect("ProdottoServlet?action=list");
        } else if ("update".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                ProdottoDTO prodotto = creaProdottoDTO(request);
                prodotto.setId(id);
                gestioneProdotto.update(prodotto);
                response.sendRedirect("ProdottoServlet?action=list");
            } else {
                request.setAttribute("errore", "ID del prodotto mancante.");
                request.getRequestDispatcher("views/errore.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errore", "Azione non supportata.");
            request.getRequestDispatcher("views/errore.jsp").forward(request, response);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
        request.getRequestDispatcher("views/errore.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        request.setAttribute("errore", "Formato ID non valido.");
        request.getRequestDispatcher("views/errore.jsp").forward(request, response);
    }
}

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            ProdottoDTO prodottoAggiornato = creaProdottoDTO(req);

            // Utilizza l'ID direttamente come stringa senza convertirlo
            ProdottoDTO prodottoEsistente = gestioneProdotto.get(id);
            if (prodottoEsistente != null) {
                gestioneProdotto.update(prodottoAggiornato);
                resp.sendRedirect("ProdottoServlet?action=list");
            } else {
                req.setAttribute("errore", "Prodotto non trovato per l'id inserito: " + id);
                req.getRequestDispatcher("views/errore.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Stampa i dettagli dell'errore nel log del server
            req.setAttribute("errorMessage", "Errore durante l'aggiornamento dell'prodotto: " + e.getMessage());
            req.getRequestDispatcher("views/errore.jsp").forward(req, resp);
        }
    }


 /*   @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            // Converti l'ID da String a int.
            int idInt = Integer.parseInt(idStr);
            // Chiamata al metodo delete del servizio, passando l'ID come int.
            gestioneProdotto.delete(idInt);
            // Redirezione alla lista dei prodotti dopo l'eliminazione.
            response.sendRedirect("ProdottoServlet?action=list");
        } catch (NumberFormatException e) {
            // Gestione dell'errore di conversione dell'ID.
            e.printStackTrace(); // Log dell'errore
            // Impostare un messaggio di errore appropriato da mostrare all'utente.
            request.setAttribute("errorMessage", "Formato ID non valido. Assicurati che l'ID sia un numero.");
            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
        } catch (SQLException e) {
            // Gestione di eventuali errori SQL.
            e.printStackTrace(); // Log dell'errore SQL
            // Impostare un messaggio di errore appropriato da mostrare all'utente.
            request.setAttribute("errorMessage", "Errore durante l'eliminazione del prodotto dal database.");
            request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
        }
    }
 
*/
    
    
    private ProdottoDTO creaProdottoDTO(HttpServletRequest req) {
        ProdottoDTO prodotto = new ProdottoDTO();
        prodotto.setNomeProdotto(req.getParameter("nomeProdotto"));
        prodotto.setPrezzo(req.getParameter("prezzo"));
        prodotto.setIva(req.getParameter("iva"));
        prodotto.setDescrizioneProdotto(req.getParameter("descrizioneProdotto"));
        prodotto.setQuantita(req.getParameter("quantita"));

        return prodotto;
    }
}
