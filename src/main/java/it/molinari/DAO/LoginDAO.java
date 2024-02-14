package it.molinari.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://151.48.169.77:3306/gestionaleScolastico?serverTimezone=UTC";
    private static final String USER = "alex";
    private static final String PASSWORD = "tmax2011";

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

    public void registerNewUser(String email, String password) throws SQLException {
        String sql = "INSERT INTO login (email, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password); 
            stmt.executeUpdate();
        }
    }

    public boolean validateUser(String email, String password) throws SQLException {
        String sql = "SELECT password FROM login WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword); 
            }
        }
        return false;
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver non trovato", e);
        }
    }
}
