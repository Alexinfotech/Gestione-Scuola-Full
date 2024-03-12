package it.molinari.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import it.molinari.model.ProdottoDTO;
import it.molinari.model.RecensioneDTO;
import it.molinari.service.RecensioneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RecensioneService gestioneRecensione = new RecensioneService();

	public RecensioneServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "create":
			    String idProdotto = request.getParameter("idProdotto");
			    String descrizioneProdotto = request.getParameter("descrizioneProdotto");
			    String nomeProdotto = request.getParameter("nomeProdotto");
			    // Assicurati che l'ID del prodotto sia stato fornito
			    if (idProdotto != null && !idProdotto.trim().isEmpty()) {
			        // Creazione di un nuovo oggetto ProdottoDTO utilizzando l'ID fornito
			        ProdottoDTO prodotto = new ProdottoDTO();
			        prodotto.setId(Integer.parseInt(idProdotto)); // Imposta l'ID del prodotto

			        // Imposta la descrizione e il nome del prodotto nell'oggetto prodotto
			        prodotto.setDescrizioneProdotto(descrizioneProdotto);
			        prodotto.setNomeProdotto(nomeProdotto);

			        // Imposta l'oggetto ProdottoDTO come attributo della richiesta
			        request.setAttribute("prodotto", prodotto);
			        // Inoltra alla pagina inserisciRecensione.jsp
			        request.getRequestDispatcher("views/recensione/inserisciRecensione.jsp").forward(request, response);
			    } else {
			        // Gestisci il caso in cui l'ID del prodotto non sia stato fornito
			        request.setAttribute("errore", "ID del prodotto non fornito.");
			        request.getRequestDispatcher("views/errore.jsp").forward(request, response);
			    }
			    break;


			case "FormSearch":
				request.setAttribute("recensione", new RecensioneDTO());
				request.getRequestDispatcher("views/recensione/SearchRecensione.jsp").forward(request, response);
				break;
				
			case "listRecensioneProdotto":
			    String id = request.getParameter("id");
			    if (id != null && !id.trim().isEmpty()) {
			        System.out.println("Tipo di dato di id: " + id.getClass().getName());
			        System.out.println("Valore di id: " + id);
			        System.out.println("Richiesta URL: " + request.getRequestURL().toString() + "?" + request.getQueryString());


			        List<RecensioneDTO> listaRecensioneProdotto = gestioneRecensione.recuperaRecensioneProdotto(id);
			        request.setAttribute("listaRecensione", listaRecensioneProdotto);
			    } else {
			        System.out.println("Il parametro 'id' è null");
			    }
			    request.getRequestDispatcher("views/recensione/listaRecensione.jsp").forward(request, response);
			    break;



		case "list":
				List<RecensioneDTO> listaRecensione = gestioneRecensione.recupera();
				request.setAttribute("listaRecensione", listaRecensione);
				request.getRequestDispatcher("views/recensione/listaRecensione.jsp").forward(request, response);
				break;
				
		/*	
			case "search":
				String testo = request.getParameter("testo");
				RecensioneDTO recensione = null;
				try {
					List<RecensioneDTO> listaRecensioni = gestioneRecensione.recupera();
					for (RecensioneDTO p : listaRecensioni) {
						if (p.getTesto().equals(testo)) {
							recensione = p;
							break;
						}
					}
					if (recensione != null) {
						request.setAttribute("prodotto", recensione);
						request.getRequestDispatcher("views/recensione/modificaRecensione.jsp").forward(request,
								response);
					} else {
						request.setAttribute("errore", "Prodotto non trovato");
						request.getRequestDispatcher("views/errore.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
					request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				}
				break;*/
			case "delete":
                String idToDelete = request.getParameter("id");
                if (idToDelete == null || idToDelete.trim().isEmpty()) {
                    request.setAttribute("errore", "ID della recensione non fornito.");
                    request.getRequestDispatcher("views/errore.jsp").forward(request, response);
                } else {
                    gestioneRecensione.delete(idToDelete);
                    response.sendRedirect("RecensioneServlet?action=list");
                }
                break;
			default:
				request.setAttribute("errore", "Azione non riconosciuta.");
				request.getRequestDispatcher("views/errore.jsp").forward(request, response);
				break;
			}
		} catch (SQLException e) {
			request.setAttribute("errore", "Errore durante l'accesso al database: " + e.getMessage());
			request.getRequestDispatcher("views/errore.jsp").forward(request, response);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");

	    try {
	        if ("create".equals(action)) {
	            RecensioneDTO recensione = creaRecensioneDTO(request);
	            gestioneRecensione.create(recensione);
	            response.sendRedirect("RecensioneServlet?action=list");
	        } else if ("update".equals(action)) {
	            // Codice omesso per brevità
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
			RecensioneDTO recensioneAggiornato = creaRecensioneDTO(req);

			// Utilizza l'ID direttamente come stringa senza convertirlo
			RecensioneDTO recensioneEsistente = gestioneRecensione.get(id);
			if (recensioneEsistente != null) {
				gestioneRecensione.update(recensioneAggiornato);
				resp.sendRedirect("RecensioneServlet?action=list");
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            // Converti l'ID da String a int.
            int idInt = Integer.parseInt(idStr);
            // Chiamata al metodo delete del servizio, passando l'ID come int.
            gestioneRecensione.delete(idInt);
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
	private RecensioneDTO creaRecensioneDTO(HttpServletRequest req) {
	    RecensioneDTO recensione = new RecensioneDTO();
	    try {
	        String prodottoIdStr = req.getParameter("idProdotto");
	        System.out.println("ID Prodotto fornito: " + prodottoIdStr); // Stampiamo l'ID del prodotto fornito
	        if (prodottoIdStr != null && !prodottoIdStr.isEmpty()) {
	            try {
	                int prodottoId = Integer.parseInt(prodottoIdStr);
	                System.out.println("Tipo di ID Prodotto: Integer"); // Stampiamo il tipo di ID del prodotto (Integer)
	                recensione.setProdottoId(prodottoId);
	            } catch (NumberFormatException ex) {
	                System.out.println("Tipo di ID Prodotto: Stringa"); // Stampiamo il tipo di ID del prodotto (Stringa)
	                recensione.setProdottoId(null); // Imposta a null in caso di errore di conversione
	            }
	        } else {
	            System.out.println("ID Prodotto non fornito o vuoto."); // Aggiungi la stampa per gestire il caso in cui idProdotto sia null o vuoto
	            // Puoi lanciare un'eccezione, impostare un valore predefinito o fare altro
	            // Ad esempio, potresti impostare il valore di prodottoId a 0 o a un altro valore predefinito
	            recensione.setProdottoId(0); // Imposta a 0 o a un altro valore predefinito
	        }
	    } catch (NumberFormatException e) {
	        // Gestisci il caso in cui il parametro "idProdotto" non sia un numero intero
	        e.printStackTrace();
	        // Puoi scegliere di lanciare un'eccezione qui o gestire l'errore in modo diverso
	    }
	    recensione.setTesto(req.getParameter("testo"));

	    return recensione;
	}





}