package it.molinari.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import it.molinari.DAO.OrdineDAO;
import it.molinari.model.OrdineDTO;

public class OrdineService {

    private OrdineDAO gestioneOrdine;

    public OrdineService() {
        this.gestioneOrdine = new OrdineDAO();
    }

 

    // Recupera tutti gli ordini di un utente, raggruppati per data
    public Map<String, List<OrdineDTO>> recuperaOrdini(int idUtente) throws SQLException {
        return gestioneOrdine.recuperaOrdini(idUtente);
    }
/*
    // Converti un Ordine in OrdineDTO
    private OrdineDTO OrdineToOrdineDTO(OrdineDTO ordine) {
        if (ordine == null) {
            return null;
        }

        // Crea una nuova istanza di OrdineDTO e imposta i suoi attributi
        OrdineDTO ordineDTO = new OrdineDTO();
        ordineDTO.setIdOrdine(ordine.getIdOrdine());
        ordineDTO.setDataOrdine(ordine.getDataOrdine());
        ordineDTO.setCostoTotale(ordine.getCostoTotale());
        
        return ordineDTO;
    }
*/
}
