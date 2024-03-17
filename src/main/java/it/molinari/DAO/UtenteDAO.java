package it.molinari.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.model.UtenteDTO;

public class UtenteDAO extends Dao implements DaoInterface<UtenteDTO> {
	/*
	 * public UtenteDTO get(String codiceFiscale) throws SQLException { String sql =
	 * "SELECT * FROM utenti WHERE codiceFiscale = ?"; try (Connection conn =
	 * getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	 * stmt.setString(1, codiceFiscale); try (ResultSet resultSet =
	 * stmt.executeQuery()) { if (resultSet.next()) { UtenteDTO utente = new
	 * UtenteDTO(); utente.setCodiceFiscale(resultSet.getString("codiceFiscale"));
	 * utente.setNome(resultSet.getString("nome"));
	 * utente.setCognome(resultSet.getString("cognome"));
	 * utente.setDataNascita(resultSet.getDate("dataNascita"));
	 * utente.setComuneDiNascita(resultSet.getString("comuneDiNascita"));
	 * utente.setProvincia(resultSet.getString("provincia"));
	 * utente.setComuneDiResidenza(resultSet.getString("comuneDiResidenza"));
	 * utente.setVia(resultSet.getString("via"));
	 * utente.setNumeroCivico(resultSet.getString("numeroCivico"));
	 * utente.setCap(resultSet.getString("cap")); return utente; } } } return null;
	 * }
	 */
	public UtenteDTO get(String codiceFiscale) throws SQLException {
		String sql = "SELECT u.*, l.ruolo FROM utenti u INNER JOIN login l ON u.login_id = l.Id WHERE u.codiceFiscale = ?";
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
					// Aggiungi anche il ruolo all'oggetto UtenteDTO
					utente.setRuolo(resultSet.getString("ruolo"));
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
		// Assumi che "TRNLSN78T07F205K" sia il codice fiscale dell'amministratore che
		// non deve essere eliminato
		String cfAmministratore = "TRNLSN78T07F205K";

		if (codiceFiscale.equals(cfAmministratore)) {
			throw new SQLException("Non è possibile eliminare l'account dell'amministratore.");
		}

		// Procedi con l'eliminazione se il codice fiscale non corrisponde a quello
		// dell'amministratore
		String sql = "DELETE FROM utenti WHERE codiceFiscale = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, codiceFiscale);

			// Elimina anche il record corrispondente dalla tabella login
			if (stmt.executeUpdate() > 0) {
				deleteLoginForUser(codiceFiscale);
			}
		}
	}

	private void deleteLoginForUser(String codiceFiscale) throws SQLException {
		String sql = "DELETE FROM login WHERE Id IN (SELECT login_id FROM utenti WHERE codiceFiscale = ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, codiceFiscale);
			stmt.executeUpdate();
		}
	}
/*
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
	*/
	/*
	@Override
	public void update(UtenteDTO utente) throws SQLException {
	    String sqlFindId = "SELECT id FROM utenti WHERE codiceFiscale = ?";
	    String sqlUpdate = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, dataNascita = ?, "
	            + "comuneDiNascita = ?, provincia = ?, comuneDiResidenza = ?, via = ?, "
	            + "numeroCivico = ?, cap = ? WHERE id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement stmtFindId = conn.prepareStatement(sqlFindId);
	         PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {

	        // Trova l'ID dell'utente corrispondente al codice fiscale
	        stmtFindId.setString(1, utente.getCodiceFiscale());
	        try (ResultSet rs = stmtFindId.executeQuery()) {
	            if (rs.next()) {
	                int idUtente = rs.getInt("id");

	                // Esegui l'aggiornamento del record utente con l'ID trovato
	                stmtUpdate.setString(1, utente.getNome());
	                stmtUpdate.setString(2, utente.getCognome());
	                stmtUpdate.setString(3, utente.getEmail());
	                stmtUpdate.setDate(4, new java.sql.Date(utente.getDataNascita().getTime()));
	                stmtUpdate.setString(5, utente.getComuneDiNascita());
	                stmtUpdate.setString(6, utente.getProvincia());
	                stmtUpdate.setString(7, utente.getComuneDiResidenza());
	                stmtUpdate.setString(8, utente.getVia());
	                stmtUpdate.setString(9, utente.getNumeroCivico());
	                stmtUpdate.setString(10, utente.getCap());
	                stmtUpdate.setInt(11, idUtente);

	                stmtUpdate.executeUpdate();
	            } else {
	                // Gestisci il caso in cui non viene trovato nessun utente con il codice fiscale specificato
	                // (ad esempio, sollevando un'eccezione o registrando un messaggio di errore)
	            }
	        }
	    }
	}
	*/
	/*
    @Override
    public void update(UtenteDTO utente) throws SQLException {
        String sql = "UPDATE utenti SET nome = ?, cognome = ?, email = ?, dataNascita = ?, "
                + "comuneDiNascita = ?, provincia = ?, comuneDiResidenza = ?, via = ?, "
                + "numeroCivico = ?, cap = ? WHERE codiceFiscale = ?";
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
 */
	@Override
	public void update(UtenteDTO utente) throws SQLException {
	    String sqlUtenti = "UPDATE utenti SET nome = ?, cognome = ?, dataNascita = ?, "
	            + "comuneDiNascita = ?, provincia = ?, comuneDiResidenza = ?, via = ?, "
	            + "numeroCivico = ?, cap = ? WHERE codiceFiscale = ?";
	    String sqlLogin = "UPDATE login SET email = ? WHERE Id = (SELECT Id FROM utenti WHERE codiceFiscale = ?)";

	    try (Connection conn = getConnection();
	         PreparedStatement stmtUtenti = conn.prepareStatement(sqlUtenti);
	         PreparedStatement stmtLogin = conn.prepareStatement(sqlLogin)) {

	        stmtUtenti.setString(1, utente.getNome());
	        stmtUtenti.setString(2, utente.getCognome());
	        stmtUtenti.setDate(3, new java.sql.Date(utente.getDataNascita().getTime()));
	        stmtUtenti.setString(4, utente.getComuneDiNascita());
	        stmtUtenti.setString(5, utente.getProvincia());
	        stmtUtenti.setString(6, utente.getComuneDiResidenza());
	        stmtUtenti.setString(7, utente.getVia());
	        stmtUtenti.setString(8, utente.getNumeroCivico());
	        stmtUtenti.setString(9, utente.getCap());
	        stmtUtenti.setString(10, utente.getCodiceFiscale());
	        stmtUtenti.executeUpdate();

	        stmtLogin.setString(1, utente.getEmail());
	        stmtLogin.setString(2, utente.getCodiceFiscale());
	        stmtLogin.executeUpdate();
	    }
	}


	/*
	 * public void inserisci(UtenteDTO utente, String idUtente) throws SQLException
	 * { String sql =
	 * "INSERT INTO utenti (codiceFiscale, nome, cognome, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap, login_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ; try (Connection conn = getConnection(); PreparedStatement stmt =
	 * conn.prepareStatement(sql)) { stmt.setString(1, utente.getCodiceFiscale());
	 * stmt.setString(2, utente.getNome()); stmt.setString(3, utente.getCognome());
	 * stmt.setDate(4, (Date) utente.getDataNascita()); stmt.setString(5,
	 * utente.getComuneDiNascita()); stmt.setString(6, utente.getProvincia());
	 * stmt.setString(7, utente.getComuneDiResidenza()); stmt.setString(8,
	 * utente.getVia()); stmt.setString(9, utente.getNumeroCivico());
	 * stmt.setString(10, utente.getCap()); stmt.setInt(11,
	 * Integer.parseInt(idUtente)); // Imposta l'idUtente direttamente
	 * 
	 * stmt.executeUpdate(); } }
	 */
	public void inserisci(UtenteDTO utente, String idUtente) throws SQLException {
		// Query per controllare se esiste già un record con lo stesso login_id
		String queryEsistenza = "SELECT COUNT(*) FROM utenti WHERE login_id = ?";

		try (Connection conn = getConnection();
				PreparedStatement stmtEsistenza = conn.prepareStatement(queryEsistenza)) {

			// Imposta il parametro della query per controllare l'esistenza del login_id
			stmtEsistenza.setInt(1, Integer.parseInt(idUtente));

			try (ResultSet rs = stmtEsistenza.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);

					// Se count è maggiore di zero, significa che esiste già un record con lo stesso
					// login_id
					if (count > 0) {
						// Gestisci il caso in cui il record esista già
						throw new SQLException("Esiste già un record con lo stesso login_id");
						// Esci dal metodo senza eseguire l'inserimento
					}
				}
			}

			// Se non esiste già un record con lo stesso login_id, procedi con l'inserimento
			String sql = "INSERT INTO utenti (codiceFiscale, nome, cognome, dataNascita, comuneDiNascita, provincia, comuneDiResidenza, via, numeroCivico, cap, login_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
	}

	public List<UtenteDTO> recupera() throws SQLException {
		List<UtenteDTO> listaUtenti = new ArrayList<>();
		String sql = "SELECT u.*, l.email FROM utenti u INNER JOIN login l ON u.login_id = l.Id WHERE l.ruolo = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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
