package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.molinari.model.ProdottoDTO;

public  class ProdottoDAO extends Dao implements DaoInterface<ProdottoDTO> {

    public ProdottoDTO get(String id) throws SQLException {
        String sql = "SELECT * FROM prodotto WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id)); // Conversione da String a Int per il parametro
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return extractProdottoFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public void create(ProdottoDTO prodotto) throws SQLException {
        String sql = "INSERT INTO prodotto (nomeProdotto, prezzo, iva, descrizioneProdotto) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodotto.getNomeProdotto());
            stmt.setString(2, prodotto.getPrezzo());
            stmt.setString(3, prodotto.getIva());
            stmt.setString(4, prodotto.getDescrizioneProdotto());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM prodotto WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id)); // Conversione da String a Int per il parametro
            stmt.executeUpdate();
        }
    }

    public void update(ProdottoDTO prodotto) throws SQLException {
        String sql = "UPDATE prodotto SET nomeProdotto = ?, prezzo = ?, iva = ?, descrizioneProdotto = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodotto.getNomeProdotto());
            stmt.setString(2, prodotto.getPrezzo());
            stmt.setString(3, prodotto.getIva());
            stmt.setString(4, prodotto.getDescrizioneProdotto());
            stmt.setInt(5, prodotto.getId());
            stmt.executeUpdate();
        }
    }

    public List<ProdottoDTO> recupera() throws SQLException {
        List<ProdottoDTO> listaProdotto = new ArrayList<>();
        String sql = "SELECT * FROM prodotto";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                listaProdotto.add(extractProdottoFromResultSet(resultSet));
            }
        }
        return listaProdotto;
    }

    private ProdottoDTO extractProdottoFromResultSet(ResultSet resultSet) throws SQLException {
        ProdottoDTO prodotto = new ProdottoDTO();
        prodotto.setId(resultSet.getInt("id"));
        prodotto.setNomeProdotto(resultSet.getString("nomeProdotto"));
        prodotto.setPrezzo(resultSet.getString("prezzo"));
        prodotto.setIva(resultSet.getString("iva"));
        prodotto.setDescrizioneProdotto(resultSet.getString("descrizioneProdotto"));
        return prodotto;
    }

}
