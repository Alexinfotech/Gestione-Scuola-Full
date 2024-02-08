package it.molinari.connessione;
import java.sql.Date;

import it.molinari.model.UtenteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestioneUtenti {
	private static final String drive = "com.mysql.cj.jdbc.Driver";

	private static final String url = "jdbc:mysql://151.48.169.77:3306/gestionaleScolastico?serverTimezone=UTC";
	private static final String login = "alex";
	private static final String paswd = "tmax2011";

	public GestioneUtenti() {
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = getConnection();
		String query = "SELECT * FROM utenti";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
			while (resultSet.next()) {
				UtenteDTO utente = new UtenteDTO();
				utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
				utente.setNome(resultSet.getString("nome"));
				utente.setCognome(resultSet.getString("cognome"));
				utente.setEmail(resultSet.getString("email"));
				utente.setDataNascita(resultSet.getDate("dataNascita"));
				utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
				utente.setProvincia(resultSet.getString("provincia"));
				utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
				utente.setVia(resultSet.getString("via"));
				utente.setNumeroCivico(resultSet.getString("numeroCivico"));
				utente.setCap(resultSet.getString("cap"));

				System.out.println(utente.toString());
			}
		}
		connection.close();
	}


	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(drive);
			connection = DriverManager.getConnection(url, login, paswd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public List<UtenteDTO> recuperaUtenti() throws SQLException, ClassNotFoundException {
	    List<UtenteDTO> listaUtenti = new ArrayList<>();
	    String sql = "SELECT * FROM utenti";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet resultSet = stmt.executeQuery()) {
	        while (resultSet.next()) {
	            UtenteDTO utente = new UtenteDTO();
	            // Imposta i campi di utente basandoti sui risultati della query
	            utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
	            utente.setNome(resultSet.getString("nome"));
	            utente.setCognome(resultSet.getString("cognome"));
	            utente.setEmail(resultSet.getString("email"));
	            utente.setDataNascita(resultSet.getDate("dataNascita"));
	            utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
	            utente.setProvincia(resultSet.getString("provincia"));
	            utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
	            utente.setVia(resultSet.getString("via"));
	            utente.setNumeroCivico(resultSet.getString("numeroCivico"));
	            utente.setCap(resultSet.getString("cap"));
	            listaUtenti.add(utente);
	        }
	    }
	    return listaUtenti;
	}

	public void inserisciUtente(UtenteDTO utente) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO utenti (codiceFiscale, nome, cognome, email, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, utente.getCodiceFiscale());
			stmt.setString(2, utente.getNome());
			stmt.setString(3, utente.getCognome());
			stmt.setString(4, utente.getEmail());
			stmt.setDate(5, (Date) utente.getDataNascita());
			stmt.setString(6, utente.getComuneDiNascita());
			stmt.setString(7, utente.getProvincia());
			stmt.setString(8, utente.getComuneDiResidenza());
			stmt.setString(9, utente.getVia());
			stmt.setString(10, utente.getNumeroCivico());
			stmt.setString(11, utente.getCap());
			stmt.executeUpdate();
		}
	}

	// CREATE
	public void createUtente(UtenteDTO utente) throws SQLException {
		String sql = "INSERT INTO utente  (codiceFiscale, nome, cognome, email, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, utente.getCodiceFiscale());
			stmt.setString(2, utente.getNome());
			stmt.setString(3, utente.getCognome());
			stmt.setString(4, utente.getEmail());
			stmt.setDate(5, utente.getDataNascita());
			stmt.setString(6, utente.getComuneDiNascita());
			stmt.setString(7, utente.getProvincia());
			stmt.setString(8, utente.getComuneDiResidenza());
			stmt.setString(9, utente.getVia());
			stmt.setString(10, utente.getNumeroCivico());
			stmt.setString(11, utente.getCap());
			stmt.executeUpdate();
		}
		// Metodi aggiuntivi per leggere, aggiornare ed eliminare utenti dal DB
	}
	// READ

	public UtenteDTO getUtente(String codiceFiscale) throws SQLException {
		// Correzione: la query SQL dovrebbe riferirsi alla tabella degli utenti, non a
		// "city"
		String sql = "SELECT * FROM utenti WHERE codiceFiscale = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, codiceFiscale);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					UtenteDTO utente = new UtenteDTO();
					utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
					utente.setNome(resultSet.getString("nome"));
					utente.setCognome(resultSet.getString("cognome"));
					utente.setEmail(resultSet.getString("email"));
					utente.setDataNascita(resultSet.getDate("dataNascita"));
					utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
					utente.setProvincia(resultSet.getString("provincia"));
					utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
					utente.setVia(resultSet.getString("via"));
					utente.setNumeroCivico(resultSet.getString("numeroCivico"));
					utente.setCap(resultSet.getString("cap"));
					// Se ci sono altri campi, aggiungerli qui

					return utente;
				}
			}
		}
		return null; // Nessun utente trovato con il codice fiscale specificato
	}
	// UPDATE

	public void updateUtente(UtenteDTO utente) throws SQLException {
	    String sql = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, dataNascita = ?, " + 
	                 "comuneDiNascita = ?, provincia = ?, comuneDiResidenza = ?, via = ?, " +
	                 "numeroCivico = ?, cap = ? WHERE codiceFiscale = ?";
	    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, utente.getNome());
	        stmt.setString(2, utente.getCognome());
	        stmt.setString(3, utente.getEmail());
	        stmt.setDate(4, new java.sql.Date(utente.getDataNascita().getTime())); // Assumendo che getDataNascita() ritorni un java.util.Date
	        stmt.setString(5, utente.getComuneDiNascita());
	        stmt.setString(6, utente.getProvincia());
	        stmt.setString(7, utente.getComuneDiResidenza());
	        stmt.setString(8, utente.getVia());
	        stmt.setString(9, utente.getNumeroCivico());
	        stmt.setString(10, utente.getCap());
	        stmt.setString(11, utente.getCodiceFiscale());

	        stmt.executeUpdate();
	    }
	}
	// DELETE

	public void deleteUtente(String codiceFiscale) throws SQLException {
	    String sql = "DELETE FROM utenti WHERE codiceFiscale = ?";
	    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, codiceFiscale);
	        stmt.executeUpdate();
	    }
	}
}