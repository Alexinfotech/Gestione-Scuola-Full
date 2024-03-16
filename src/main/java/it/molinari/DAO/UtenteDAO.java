package it.molinari.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.model.LoginDTO;
import it.molinari.model.UtenteDTO;

public class UtenteDAO extends Dao implements DaoInterface<UtenteDTO> {

	public UtenteDTO get(String codiceFiscale) throws SQLException {
		String sql = "SELECT * FROM utenti WHERE codiceFiscale = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, codiceFiscale);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					UtenteDTO utente = new UtenteDTO();
					utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
					utente.setNome(resultSet.getString("nome"));
					utente.setCognome(resultSet.getString("cognome"));
					utente.setDataNascita(resultSet.getDate("dataNascita"));
					utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
					utente.setProvincia(resultSet.getString("provincia"));
					utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
					utente.setVia(resultSet.getString("via"));
					utente.setNumeroCivico(resultSet.getString("numeroCivico"));
					utente.setCap(resultSet.getString("cap"));
					return utente;
				}
			}
		}
		return null;
	}

	@Override
	public void create(UtenteDTO utente) throws SQLException {
		String sql = "INSERT INTO utenti (codiceFiscale, nome, cognome, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap , login_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, utente.getCodiceFiscale());
			stmt.setString(2, utente.getNome());
			stmt.setString(3, utente.getCognome());
			stmt.setDate(4, (Date) utente.getDataNascita());
			stmt.setString(5, utente.getComuneDiNascita());
			stmt.setString(6, utente.getProvincia());
			stmt.setString(7, utente.getComuneDiResidenza());
			stmt.setString(8, utente.getVia());
			stmt.setString(9, utente.getNumeroCivico());
			stmt.setString(10, utente.getCap());

			stmt.executeUpdate();
		}
	}

	public void delete(String codiceFiscale) throws SQLException {
		String sql = "DELETE FROM utenti WHERE codiceFiscale = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, codiceFiscale);
			stmt.executeUpdate();
		}
	}

	@Override
	public void update(UtenteDTO utente) throws SQLException {
		String sql = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, dataNascita = ?, "
				+ "comuneDiNascita = ?, provincia = ?, comuneDiResidenza = ?, via = ?, "
				+ "numeroCivico = ?, cap = ? WHERE codiceFiscale = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, utente.getNome());
			stmt.setString(2, utente.getCognome());
			stmt.setString(3, utente.getEmail());
			stmt.setDate(4, new java.sql.Date(utente.getDataNascita().getTime())); // Assumendo che getDataNascita()
																					// ritorni un java.util.Date
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

	public void inserisci(UtenteDTO utente, String idUtente) throws SQLException {
		String sql = "INSERT INTO utenti (codiceFiscale, nome, cognome, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap, login_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, utente.getCodiceFiscale());
			stmt.setString(2, utente.getNome());
			stmt.setString(3, utente.getCognome());
			stmt.setDate(4, (Date) utente.getDataNascita());
			stmt.setString(5, utente.getComuneDiNascita());
			stmt.setString(6, utente.getProvincia());
			stmt.setString(7, utente.getComuneDiResidenza());
			stmt.setString(8, utente.getVia());
			stmt.setString(9, utente.getNumeroCivico());
			stmt.setString(10, utente.getCap());
			stmt.setInt(11, Integer.parseInt(idUtente)); // Imposta l'idUtente direttamente

			stmt.executeUpdate();
		}
	}

	public List<UtenteDTO> recupera() throws SQLException {
	    List<UtenteDTO> listaUtenti = new ArrayList<>();
	    String sql = "SELECT u.*, l.email FROM utenti u INNER JOIN login l ON u.login_id = l.Id WHERE l.ruolo = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, "utente navigatore");
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            while (resultSet.next()) {
	                UtenteDTO utente = new UtenteDTO();
	                utente.setEmail(resultSet.getString("email"));
	                utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
	                utente.setNome(resultSet.getString("nome"));
	                utente.setCognome(resultSet.getString("cognome"));
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
	    }
	    return listaUtenti;
	}
	public List<UtenteDTO> recuperaMagazzinieri() throws SQLException {
	    List<UtenteDTO> listaMagazzinieri = new ArrayList<>();
	    String sql = "SELECT u.*, l.email FROM utenti u INNER JOIN login l ON u.login_id = l.Id WHERE l.ruolo = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, "magazziniere");
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            while (resultSet.next()) {
	                UtenteDTO utente = new UtenteDTO();
	                utente.setEmail(resultSet.getString("email"));
	                utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
	                utente.setNome(resultSet.getString("nome"));
	                utente.setCognome(resultSet.getString("cognome"));
	                utente.setDataNascita(resultSet.getDate("dataNascita"));
	                utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
	                utente.setProvincia(resultSet.getString("provincia"));
	                utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
	                utente.setVia(resultSet.getString("via"));
	                utente.setNumeroCivico(resultSet.getString("numeroCivico"));
	                utente.setCap(resultSet.getString("cap"));
	                listaMagazzinieri.add(utente);
	            }
	        }
	    }
	    return listaMagazzinieri;
	}

	public UtenteDTO recuperaIndirizzo(int idUtente) throws SQLException, ClassNotFoundException {
	    UtenteDTO utente = null;
	    String sql = "SELECT nome, cognome, via, numeroCivico, comuneDiResidenza, cap FROM utenti WHERE login_id = ?";
	    
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setInt(1, idUtente); // Assicurati che idUtente sia un int o convertilo
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                utente = new UtenteDTO();
	                utente.setNome(rs.getString("nome"));
	                utente.setCognome(rs.getString("cognome"));
	                utente.setVia(rs.getString("via"));
	                utente.setNumeroCivico(rs.getString("numeroCivico"));

	                
	                utente.setComuneDiResidenza(rs.getString("comuneDiResidenza"));
	                utente.setCap(rs.getString("cap"));
	            }
	        }
	    }
	    return utente;
	}


}
