package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.molinari.model.CarrelloDTO;

public class CarrelloDAO extends Dao implements DaoInterface<CarrelloDTO> {

    @Override
    public CarrelloDTO get(String id) throws SQLException {
        CarrelloDTO carrello = null;
        String sql = "SELECT * FROM carrello WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    carrello = new CarrelloDTO();
                    carrello.setId(resultSet.getInt("id"));
                    carrello.setUtenteId(resultSet.getInt("utenteId"));
                    carrello.setProdottoId(resultSet.getInt("prodottoId"));
                    carrello.setQuantita(resultSet.getInt("quantita"));
                    carrello.setDataAggiunta(resultSet.getTimestamp("dataAggiunta"));
                }
            }
        }
        return carrello;
    }

    @Override
    public void create(CarrelloDTO carrello) throws SQLException {
        String sql = "INSERT INTO carrello (utenteId, prodottoId, quantita, dataAggiunta) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carrello.getUtenteId());
            stmt.setInt(2, carrello.getProdottoId());
            stmt.setInt(3, carrello.getQuantita());
            stmt.setTimestamp(4, new java.sql.Timestamp(carrello.getDataAggiunta().getTime()));
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM carrello WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(CarrelloDTO carrello) throws SQLException {
        String sql = "UPDATE carrello SET utenteId = ?, prodottoId = ?, quantita = ?, dataAggiunta = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carrello.getUtenteId());
            stmt.setInt(2, carrello.getProdottoId());
            stmt.setInt(3, carrello.getQuantita());
            stmt.setTimestamp(4, new java.sql.Timestamp(carrello.getDataAggiunta().getTime()));
            stmt.setInt(5, carrello.getId());
            stmt.executeUpdate();
        }
    }

    // Metodo per recuperare tutti gli elementi nel carrello di un utente
    public List<CarrelloDTO> getItemsByUtenteId(int utenteId) throws SQLException {
        List<CarrelloDTO> carrelloItems = new ArrayList<>();
        String sql = "SELECT * FROM carrello WHERE utenteId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, utenteId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    CarrelloDTO carrello = new CarrelloDTO();
                    carrello.setId(resultSet.getInt("id"));
                    carrello.setUtenteId(resultSet.getInt("utenteId"));
                    carrello.setProdottoId(resultSet.getInt("prodottoId"));
                    carrello.setQuantita(resultSet.getInt("quantita"));
                    carrello.setDataAggiunta(resultSet.getTimestamp("dataAggiunta"));
                    carrelloItems.add(carrello);
                }
            }
        }
        return carrelloItems;
    }
}
