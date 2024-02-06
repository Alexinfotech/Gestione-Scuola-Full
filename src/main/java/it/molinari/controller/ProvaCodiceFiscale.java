package it.molinari.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProvaCodiceFiscale")
public class ProvaCodiceFiscale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProvaCodiceFiscale() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String sesso = request.getParameter("sesso");
		String comuneDiNascita = request.getParameter("comuneDiNascita");
		String provincia = request.getParameter("provincia");
		String dataNascita = request.getParameter("dataNascita");
		String apiUrl = costruisciUrlApi(cognome, nome, sesso, comuneDiNascita, provincia, dataNascita);

		String codiceFiscale = eseguiChiamataApi(apiUrl);

		request.setAttribute("codiceFiscaleGenerato", codiceFiscale);

		RequestDispatcher dispatcher = request.getRequestDispatcher("views/utente/ProvaCF/jsp.jsp");
		dispatcher.forward(request, response);
	}

	private String costruisciUrlApi(String cognome, String nome, String sesso, String comuneDiNascita, String provincia,
			String dataNascita) throws UnsupportedEncodingException {
		return "http://api.miocodicefiscale.com/calculate?" + "lname=" + encodeURIComponent(cognome) + "&fname="
				+ encodeURIComponent(nome) + "&gender=" + encodeURIComponent(sesso) + "&city="
				+ encodeURIComponent(comuneDiNascita) + "&state=" + encodeURIComponent(provincia) + "&day="
				+ encodeURIComponent(dataNascita.split("-")[2]) + "&month="
				+ encodeURIComponent(dataNascita.split("-")[1]) + "&year="
				+ encodeURIComponent(dataNascita.split("-")[0]) + "&omocodia_level=1"
				+ "&access_token=120d80c4786d1f557e2ca6b3dc2a487b507c99236d8cf1666e39164369d84777556";
	}

	private String eseguiChiamataApi(String apiUrl) throws IOException {
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return estraiCodiceFiscaleDaRisposta(response.toString());
		} else {
			return "Errore nella chiamata API: Codice risposta " + responseCode;
		}
	}

	private String estraiCodiceFiscaleDaRisposta(String response) {
		try {
			// Crea un JSONObject dalla stringa di risposta in pace
			JSONObject jsonResponse = new JSONObject(response);

			if (jsonResponse.optBoolean("status") && jsonResponse.has("data")) {
				JSONObject jsonData = jsonResponse.getJSONObject("data");
				if (jsonData.has("cf")) {
					return jsonData.getString("cf"); // Forese Restituisce il codice fiscale, ma magari!!
				}
			}
			return "Errore: Codice fiscale non trovato nella risposta dell'API.";
		} catch (Exception e) {
			// Gestisco eventuali eccezioni se riesco hahaahha
			return "Errore durante il parsing della risposta JSON: " + e.getMessage();
		}
	}

	private String encodeURIComponent(String value) throws UnsupportedEncodingException {
		return java.net.URLEncoder.encode(value, java.nio.charset.StandardCharsets.UTF_8.toString());
	}
}
