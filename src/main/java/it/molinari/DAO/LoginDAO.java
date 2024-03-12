package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    public String validateUser(String email, String password) throws SQLException {
        String sql = "SELECT password, ruolo FROM login WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String ruolo = resultSet.getString("ruolo");
                // Controlla la corrispondenza della password
                if (password.equals(storedPassword)) {
                    // La password è corretta, restituisci il ruolo dell'utente
                    return ruolo;
                }
            }
        }
        return null; // Restituisci null se l'utente non esiste o la password non corrisponde
    }
}
