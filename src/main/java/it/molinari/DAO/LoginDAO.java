package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.molinari.model.LoginDTO;

public class LoginDAO extends Dao implements LoginDAOInterface {

    @Override
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM login WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public void registerNewUser(String email, String password) throws SQLException {
        String sql = "INSERT INTO login (email, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }
    }
	public void registraMagazziniere(String email, String password) throws SQLException {
	    String sql = "INSERT INTO login (email, password, ruolo) VALUES (?, ?, ?)";
	    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        stmt.setString(3, "magazziniere"); // Imposta il ruolo come "magazziniere"
	        stmt.executeUpdate();
	    }
	}

    @Override
    public LoginDTO validateUser(String email, String password) throws SQLException {
        String sql = "SELECT id, password, ruolo FROM login WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int idUtente = resultSet.getInt("id");
                String storedPassword = resultSet.getString("password");
                String ruolo = resultSet.getString("ruolo");
                if (password.equals(storedPassword)) {
                    LoginDTO user = new LoginDTO();
                    user.setEmailLogin(email);
                    user.setPassword(password); // Considera la sicurezza qui
                    user.setIdUtente(idUtente);
                    user.setRuolo(ruolo);
                    return user;
                }
            }
        }
        return null;
    }
    public boolean isAnagraficaCompleta(int idUtente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM utenti WHERE login_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUtente);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    public LoginDTO findById(int id) throws SQLException {
        String sql = "SELECT * FROM login WHERE id = ?"; // Assumi che la colonna per l'ID sia chiamata 'id'.
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    LoginDTO user = new LoginDTO();
                    user.setIdUtente(resultSet.getInt("id"));
                    user.setEmailLogin(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password")); // Nota sulla sicurezza sotto.
                    user.setRuolo(resultSet.getString("ruolo"));
                    return user;
                }
            }
        }
        return null; // Ritorna null se nessun utente è trovato con quell'ID.
    }


}
