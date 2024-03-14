package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.molinari.model.ProdottoDTO;

public  class CarrelloDAO extends Dao implements DaoInterface<ProdottoDTO> {

    public ProdottoDTO get(String id) throws SQLException {
        String sql = "SELECT * FROM carrello WHERE id = ?";
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
/*
    public void create(int loginId, int prodottoId, int quantita) throws SQLException {
        String sql = "INSERT INTO carrello (login_id, prodotto_id, quantita) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loginId);
            stmt.setInt(2, prodottoId);
            stmt.setInt(3, quantita);
            stmt.executeUpdate();
        }
    }
*/
    public void create(int loginId, int prodottoId, int quantita) throws SQLException {
        // Prima, inserisci il nuovo elemento nel carrello
        String sqlCarrello = "INSERT INTO carrello (login_id, prodotto_id, quantita) VALUES (?, ?, ?)";
        // Secondo, aggiorna la quantità disponibile del prodotto
        String sqlProdotto = "UPDATE prodotto SET quantita = quantita - ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmtCarrello = null;
        PreparedStatement stmtProdotto = null;

        try {
            conn = getConnection();
            // Disabilita l'auto-commit per gestire le operazioni come una transazione
            conn.setAutoCommit(false);

            // Inserimento nel carrello
            stmtCarrello = conn.prepareStatement(sqlCarrello);
            stmtCarrello.setInt(1, loginId);
            stmtCarrello.setInt(2, prodottoId);
            stmtCarrello.setInt(3, quantita);
            stmtCarrello.executeUpdate();

            // Aggiornamento quantità prodotto
            stmtProdotto = conn.prepareStatement(sqlProdotto);
            stmtProdotto.setInt(1, quantita);
            stmtProdotto.setInt(2, prodottoId);
            stmtProdotto.executeUpdate();

            // Commit delle modifiche se entrambe le operazioni sono riuscite
            conn.commit();
        } catch (SQLException e) {
            // In caso di errore, effettua il rollback delle modifiche
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; // Rilancia l'eccezione per gestirla ulteriormente o notificarla
        } finally {
            // Ripristina l'auto-commit (importante se riutilizzi la connessione)
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Chiudi le risorse in ordine inverso alla loro apertura
            if (stmtProdotto != null) {
                try { stmtProdotto.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (stmtCarrello != null) {
                try { stmtCarrello.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM carrello WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id)); // Conversione da String a Int per il parametro
            stmt.executeUpdate();
        }
    }

    public List<ProdottoDTO> recuperaCarrello() throws SQLException {
        List<ProdottoDTO> listaProdotto = new ArrayList<>();
        // SQL aggiornato per eseguire la join con la tabella prodotto
        String sql = "SELECT p.*, c.quantita FROM carrello c JOIN prodotto p ON c.prodotto_id = p.id";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {
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
        prodotto.setQuantita(resultSet.getString("quantita"));
        return prodotto;
    }


}
