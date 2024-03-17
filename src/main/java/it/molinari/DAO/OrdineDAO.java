package it.molinari.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.molinari.model.OrdineDTO;
import it.molinari.DAO.OrdineDAO;

/*public class OrdineDAO extends Dao implements DaoInterface<OrdineDTO> {


    public Map<String, List<OrdineDTO>> recuperaOrdini(int idUtente) throws SQLException {
        Map<String, List<OrdineDTO>> ordiniPerData = new HashMap<>();
        String sql = "SELECT DATE(o.data_acquisto) AS data, o.id_ordine, p.nome_prodotto, p.prezzo " +
                       "FROM ordini_effettuati o " +
                       "INNER JOIN prodotto p ON o.id_prodotto = p.id_prodotto " +
                       "WHERE o.id_login = ? " +
                       "ORDER BY o.data_acquisto DESC";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUtente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String data = rs.getString("data");
                    int idOrdine = rs.getInt("id_ordine");
                    String nomeProdotto = rs.getString("nome_prodotto");
                    double prezzo = rs.getDouble("prezzo");
                    
                    // Ricerca se l'ordine esiste già per la data specifica
                    List<OrdineDTO> ordiniDelGiorno = ordiniPerData.getOrDefault(data, new ArrayList<>());
                    OrdineDTO ordineEsistente = ordiniDelGiorno.stream()
                                                                .filter(o -> o.getIdOrdine().equals(idOrdine))
                                                                .findFirst()
                                                                .orElse(null);
                    
                    if (ordineEsistente == null) {
                        // Se l'ordine non esiste, creane uno nuovo
                        ordineEsistente = new OrdineDTO();
                        ordineEsistente.setIdOrdine(idOrdine);
                        ordineEsistente.setDataOrdine(data);
                        ordiniDelGiorno.add(ordineEsistente);
                        ordiniPerData.putIfAbsent(data, ordiniDelGiorno);
                    }
                    
                    // Aggiungi i dettagli del prodotto all'ordine esistente
                    ordineEsistente.addDettaglioProdotto(nomeProdotto, prezzo);
                }
            }
        }
        
        return ordiniPerData;
    }
*/
public class OrdineDAO extends Dao implements DaoInterface<OrdineDTO> {

	public Map<String, List<OrdineDTO>> recuperaOrdini(int idUtente) throws SQLException {
		Map<String, List<OrdineDTO>> ordiniPerData = new HashMap<>();
		String sql = "SELECT DATE(o.data_acquisto) AS data, o.id_ordine, p.nomeProdotto, p.prezzo "
				+ "FROM ordini_effettuati o " + "INNER JOIN prodotto p ON o.id_prodotto = p.id "
				+ "WHERE o.id_login = ? " + "ORDER BY o.data_acquisto DESC";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idUtente);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String data = rs.getString("data");
					int idOrdine = rs.getInt("id_ordine");
					String nomeProdotto = rs.getString("nomeProdotto");
					double prezzo = rs.getDouble("prezzo");

					List<OrdineDTO> ordiniDelGiorno = ordiniPerData.computeIfAbsent(data, k -> new ArrayList<>());
					OrdineDTO ordineEsistente = trovaOrdineEsistente(ordiniDelGiorno, idOrdine);

					if (ordineEsistente == null) {
						ordineEsistente = new OrdineDTO();
						ordineEsistente.setIdOrdine(idOrdine);
						ordineEsistente.setDataOrdine(data);
						ordiniDelGiorno.add(ordineEsistente);
					}

					ordineEsistente.addDettaglioProdotto(nomeProdotto, prezzo);
				}
			}
		}

		return ordiniPerData;
	}

	private OrdineDTO trovaOrdineEsistente(List<OrdineDTO> ordini, int idOrdine) {
		for (OrdineDTO ordine : ordini) {
			if (ordine.getIdOrdine() == idOrdine) {
				return ordine;
			}
		}
		return null;
	}

	@Override
	public OrdineDTO get(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(OrdineDTO t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(OrdineDTO t) throws SQLException {
		// TODO Auto-generated method stub

	}

}
