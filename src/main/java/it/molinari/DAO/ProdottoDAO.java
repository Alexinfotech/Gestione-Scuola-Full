package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.molinari.model.ProdottoDTO;
import it.molinari.model.UtenteDTO;

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
        String sql = "INSERT INTO prodotto (nomeProdotto, prezzo, iva, descrizioneProdotto,quantita) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodotto.getNomeProdotto());
            stmt.setString(2, prodotto.getPrezzo());
            stmt.setString(3, prodotto.getIva());
            stmt.setString(4, prodotto.getDescrizioneProdotto());
            stmt.setString(5,prodotto.getQuantita());
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
        String sql = "UPDATE prodotto SET nomeProdotto = ?, prezzo = ?, iva = ?, descrizioneProdotto = ?, quantita= ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodotto.getNomeProdotto());
            stmt.setString(2, prodotto.getPrezzo());
            // Assicurati che il metodo getIva() restituisca una stringa, altrimenti potrebbe essere necessario convertirlo
            stmt.setString(3, prodotto.getIva()); 
            stmt.setString(4, prodotto.getDescrizioneProdotto());
            // Assicurati che getQuantita() restituisca una stringa. Se restituisce un tipo numerico (es. Integer), usa stmt.setInt() o stmt.setObject()
            stmt.setString(5, prodotto.getQuantita()); 
            stmt.setInt(6, prodotto.getId());
            stmt.executeUpdate();
        }
    }
/*
    public void acquista(ProdottoDTO prodotto) throws SQLException {
        // Ottiene l'ID del prodotto dall'oggetto ProdottoDTO
        int prodottoId = prodotto.getId();

        // Prima recupera l'attuale quantità del prodotto
        String sqlGetQuantita = "SELECT quantita FROM prodotto WHERE id = ?";
        int quantita = 0;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlGetQuantita)) {
            // Utilizza prodottoId per impostare il parametro della query
            stmt.setInt(1, prodottoId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    quantita = resultSet.getInt("quantita");
                }
            }
        }

        // Controlla se la quantità è maggiore di 0 per evitare quantità negative
        if (quantita > 0) {
            // Decrementa la quantità di 1
            quantita--;

            // Aggiorna la quantità nel database
            String sqlUpdateQuantita = "UPDATE prodotto SET quantita = ? WHERE id = ?";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlUpdateQuantita)) {
                stmt.setInt(1, quantita);
                stmt.setInt(2, prodottoId);
                stmt.executeUpdate();
            }
        } else {
            throw new SQLException("Quantità insufficiente per l'acquisto.");
        }
    }
*/
    public void acquista(ProdottoDTO prodotto, int idUtente) throws SQLException {
        // Ottiene l'ID del prodotto dall'oggetto ProdottoDTO
        int prodottoId = prodotto.getId();

        // Prima recupera l'attuale quantità del prodotto
        String sqlGetQuantita = "SELECT quantita FROM prodotto WHERE id = ?";
        int quantita = 0;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlGetQuantita)) {
            // Utilizza prodottoId per impostare il parametro della query
            stmt.setInt(1, prodottoId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    quantita = resultSet.getInt("quantita");
                }
            }
        }

        // Controlla se la quantità è maggiore di 0 per evitare quantità negative
        if (quantita > 0) {
            // Decrementa la quantità di 1
            quantita--;

            // Aggiorna la quantità nel database
            String sqlUpdateQuantita = "UPDATE prodotto SET quantita = ? WHERE id = ?";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlUpdateQuantita)) {
                stmt.setInt(1, quantita);
                stmt.setInt(2, prodottoId);
                stmt.executeUpdate();
            }

            // Inserisce l'acquisto nella tabella ordini_effettuati
            String sqlInsertOrdine = "INSERT INTO ordini_effettuati (id_login, id_prodotto) VALUES (?, ?)";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlInsertOrdine)) {
                // Qui dovresti impostare l'ID dell'utente che ha effettuato l'acquisto.
                // Supponiamo che l'ID dell'utente sia disponibile come costante o da un altro luogo.
                //int idUtente = 1; // Esempio: ID dell'utente che ha effettuato l'acquisto
                stmt.setInt(1, idUtente);
                stmt.setInt(2, prodottoId);
                stmt.executeUpdate();
            }
        } else {
            throw new SQLException("Quantità insufficiente per l'acquisto.");
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
        prodotto.setQuantita(resultSet.getString("quantita"));
        return prodotto;
    }

}
