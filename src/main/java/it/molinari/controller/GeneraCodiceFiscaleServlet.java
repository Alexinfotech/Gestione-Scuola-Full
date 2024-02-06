package it.molinari.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import it.molinari.model.UtenteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GeneraCodiceFiscaleServlet")
public class GeneraCodiceFiscaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, UtenteDTO> mappaUtenti = new HashMap<>();

	public GeneraCodiceFiscaleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "create":
			request.setAttribute("utente", new UtenteDTO());
			request.getRequestDispatcher("views/utente/UtenteCF.jsp").forward(request, response);
			break;
		case "list":
			request.setAttribute("mappaUtenti", mappaUtenti);
			request.getRequestDispatcher("views/utente/UtenteCF.jsp").forward(request, response);
			break;
		}
	}

	/*
	 * @Override protected void doPost(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { String
	 * nome = request.getParameter("nome"); if
	 * ("create".equals(request.getParameter("action"))) { if
	 * (mappaUtenti.containsKey(nome)) { request.setAttribute("errore",
	 * "Codice fiscale già esistente."); request.setAttribute("utente",
	 * creaUtenteDTO(request));
	 * request.getRequestDispatcher("views/utente/UtenteCF.jsp").forward(request,
	 * response); return; }
	 * 
	 * UtenteDTO utente = creaUtenteDTO(request); // Qui aggiungi il codice per la
	 * generazione del codice fiscale tramite API // esterna String codiceFiscale =
	 * generaCodiceFiscale(utente); utente.setCodiceFiscale(codiceFiscale);
	 * mappaUtenti.put(nome, utente);
	 * 
	 * request.setAttribute("utente", utente);
	 * request.getRequestDispatcher("views/utente/UtenteCF.jsp").forward(request,
	 * response); } }
	 * 
	 */
	@SuppressWarnings("unused")
	private String generaCodiceFiscale(UtenteDTO utente) {
		String apiUrl = "http://api.miocodicefiscale.com/calculate" + "?lname=" + utente.getCognome() + "&fname="
				+ utente.getNome() + "&gender=" + utente.getSesso() + "&city=" + utente.getComuneDiNascita() + "&state="
				+ utente.getProvincia()
				// + "&day=" + utente.getGiornoDiNascita()
				// + "&month=" + utente.getMeseDiNascita()
				// + "&year=" + utente.getAnnoDiNascita()
				+ "&access_token=tua-chiave-API"; // Sostituisci con la tua chiave API

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(apiUrl);
			HttpResponse response = httpClient.execute(request);
			String json = EntityUtils.toString(response.getEntity());
			JSONObject jsonResponse = new JSONObject(json);

			if (jsonResponse.getBoolean("status")) {
				return jsonResponse.getJSONArray("data").getString(0); // Restituisce il codice fiscale
			} else {
				System.out.println("Errore: " + jsonResponse.getString("message"));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
