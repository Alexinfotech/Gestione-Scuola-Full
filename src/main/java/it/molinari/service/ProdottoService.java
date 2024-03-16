package it.molinari.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.DAO.ProdottoDAO;
import it.molinari.model.ProdottoDTO;
import it.molinari.model.UtenteDTO;

public class ProdottoService {

    private ProdottoDAO gestioneProdotto;

    public ProdottoService() {
        this.gestioneProdotto = new ProdottoDAO();
    }

    public ProdottoDTO get(String id) throws SQLException {
        ProdottoDTO prodotto = gestioneProdotto.get(id);
        return ProdottoToProdottoDTO(prodotto);
    }

    public void create(ProdottoDTO prodottoDTO) throws SQLException {
        ProdottoDTO prodotto = ProdottoDTOToProdotto(prodottoDTO);
        gestioneProdotto.create(prodotto);
    }

    public void update(ProdottoDTO prodottoDTO) throws SQLException {
        ProdottoDTO prodotto = ProdottoDTOToProdotto(prodottoDTO);
        gestioneProdotto.update(prodotto);
    }
/*    public void acquista(ProdottoDTO prodottoDTO, String idUtente) throws SQLException {
        ProdottoDTO prodotto = ProdottoDTOToProdotto(prodottoDTO);
        
        gestioneProdotto.acquista(prodotto);
    }*/
    public void acquista(ProdottoDTO prodottoDTO, int idUtente) throws SQLException {
        ProdottoDTO prodotto = ProdottoDTOToProdotto(prodottoDTO);

        // Qui puoi fare ciò che devi fare con l'ID dell'utente, ad esempio salvarlo o utilizzarlo per qualche operazione

        gestioneProdotto.acquista(prodotto, idUtente);
    }


    /*public void delete(String id) throws SQLException {
        gestioneProdotto.delete(id);
    }*/
    public void delete(String id, String ruolo) throws SQLException, SecurityException {
        if (!Ruolo.MAGAZZINIERE.equals(ruolo)) {
            throw new SecurityException("Operazione non autorizzata: solo i magazzinieri possono eliminare prodotti.");
        }
        gestioneProdotto.delete(id);
    }
    /*
    public UtenteDTO recuperaIndirizzo(int idUtente) throws SQLException, ClassNotFoundException {
        return gestioneUtenti.recuperaIndirizzo(idUtente);
    }
*/
    public void inserisci(ProdottoDTO prodottoDTO) throws SQLException, ClassNotFoundException {
        ProdottoDTO prodotto = ProdottoDTOToProdotto(prodottoDTO);
        gestioneProdotto.create(prodotto);
    }

    public List<ProdottoDTO> recupera() throws ClassNotFoundException, SQLException {
        List<ProdottoDTO> listaProdotto = gestioneProdotto.recupera();
        List<ProdottoDTO> listaProdottoDTO = new ArrayList<>();
        for (ProdottoDTO prodotto : listaProdotto) {
            ProdottoDTO dto = ProdottoToProdottoDTO(prodotto);
            listaProdottoDTO.add(dto);
        }
        return listaProdottoDTO;
    }

    private ProdottoDTO ProdottoToProdottoDTO(ProdottoDTO prodotto) {
        if (prodotto == null) {
            return null;
        }
        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.setId(prodotto.getId());
        prodottoDTO.setNomeProdotto(prodotto.getNomeProdotto());
        prodottoDTO.setPrezzo(prodotto.getPrezzo());
        prodottoDTO.setIva(prodotto.getIva());
        prodottoDTO.setDescrizioneProdotto(prodotto.getDescrizioneProdotto());
        prodottoDTO.setQuantita(prodotto.getQuantita());

        return prodottoDTO;
    }

    private ProdottoDTO ProdottoDTOToProdotto(ProdottoDTO prodottoDTO) {
        ProdottoDTO prodotto = new ProdottoDTO();
        prodotto.setId(prodottoDTO.getId());
        prodotto.setNomeProdotto(prodottoDTO.getNomeProdotto());
        prodotto.setPrezzo(prodottoDTO.getPrezzo());
        prodotto.setIva(prodottoDTO.getIva());
        prodotto.setDescrizioneProdotto(prodottoDTO.getDescrizioneProdotto());
        prodotto.setQuantita(prodottoDTO.getQuantita());

        return prodotto;
    }
}
