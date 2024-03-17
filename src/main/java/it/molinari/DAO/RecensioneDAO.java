package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.model.ProdottoDTO;
import it.molinari.model.RecensioneDTO;

public class RecensioneDAO extends Dao implements DaoInterface<RecensioneDTO> {

	public RecensioneDTO get(String id) throws SQLException {
		String sql = "SELECT * FROM recensioni WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, Integer.parseInt(id)); // Conversione da String a Int per il parametro
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					return extractRecensioneFromResultSet(resultSet);
				}
			}
		}
		return null;
	}

	public void update(RecensioneDTO recensione) throws SQLException {

	}

	public void create(RecensioneDTO recensione) throws SQLException {
		String sql = "INSERT INTO recensioni (prodottoId, testo) VALUES ( ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, recensione.getProdottoId());
			stmt.setString(2, recensione.getTesto());
			stmt.executeUpdate();
		}
	}

	public void delete(String id) throws SQLException {
		String sql = "DELETE FROM recensioni WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, Integer.parseInt(id)); // Conversione da String a Int per il parametro
			stmt.executeUpdate();
		}
	}

	public List<RecensioneDTO> recupera() throws SQLException {
		List<RecensioneDTO> listaRecensione = new ArrayList<>();
		String sql = "SELECT * FROM recensioni";
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultSet = stmt.executeQuery()) {
			while (resultSet.next()) {
				listaRecensione.add(extractRecensioneFromResultSet(resultSet));
			}
		}
		return listaRecensione;
	}

	public List<RecensioneDTO> recuperaRecensioneProdotto(String id) throws SQLException {
		List<RecensioneDTO> listaRecensioneProdotto = new ArrayList<>();
		String sql = "SELECT * FROM recensioni WHERE prodottoId = ?";

		System.out.println("Preparazione della query SQL nella DAO: " + sql);
		System.out.println("Valore di id fornito alla DAO: " + id);

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Converti la stringa id in un intero e imposta il parametro della query.
			int prodottoId = Integer.parseInt(id);
			stmt.setInt(1, prodottoId);

			System.out.println("ID prodotto impostato per la query: " + prodottoId);

			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					listaRecensioneProdotto.add(extractRecensioneFromResultSet(resultSet));
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Errore nella conversione dell'ID prodotto da String a Int: " + id);
			e.printStackTrace();
			throw new SQLException("Errore nella conversione dell'ID prodotto da String a Int.", e);
		}

		System.out.println(
				"Numero di recensioni trovate per il prodotto con ID " + id + ": " + listaRecensioneProdotto.size());
		return listaRecensioneProdotto;
	}

	private RecensioneDTO extractRecensioneFromResultSet(ResultSet resultSet) throws SQLException {
		RecensioneDTO recensione = new RecensioneDTO();
		recensione.setId(resultSet.getInt("id"));

		recensione.setProdottoId(resultSet.getInt("prodottoId"));
		recensione.setTesto(resultSet.getString("testo"));

		return recensione;
	}

}
