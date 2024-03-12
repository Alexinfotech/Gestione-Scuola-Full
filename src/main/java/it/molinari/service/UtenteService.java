package it.molinari.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.DAO.UtenteDAO;
import it.molinari.model.UtenteDTO;

public class UtenteService {

    private UtenteDAO gestioneUtenti;

    public UtenteService() {
        this.gestioneUtenti = new UtenteDAO();
    }

    public UtenteDTO get(String codiceFiscale) throws SQLException {
        UtenteDTO utente = gestioneUtenti.get(codiceFiscale);
        return UtenteToUtenteDTO(utente);
    }

    public void create(UtenteDTO utenteDTO) throws SQLException {
        UtenteDTO utente = UtenteDTOToUtente(utenteDTO);
        gestioneUtenti.inserisci(utente);
    }

    public void update(UtenteDTO utenteDTO) throws SQLException {
        UtenteDTO utente = UtenteDTOToUtente(utenteDTO);
        gestioneUtenti.update(utente);
    }

    public void delete(String codiceFiscale) throws SQLException {
        gestioneUtenti.delete(codiceFiscale);
    }

    public void inserisci(UtenteDTO utenteDTO) throws SQLException, ClassNotFoundException {
        UtenteDTO utente = UtenteDTOToUtente(utenteDTO);
        gestioneUtenti.inserisci(utente);
    }

    public List<UtenteDTO> recupera() throws ClassNotFoundException, SQLException {
        List<UtenteDTO> listaUtenti = gestioneUtenti.recupera();
        List<UtenteDTO> listaUtentiDTO = new ArrayList<>();
        for (UtenteDTO utente : listaUtenti) {
            UtenteDTO dto = UtenteToUtenteDTO(utente);
            listaUtentiDTO.add(dto);
        }
        return listaUtentiDTO;
    }

    private UtenteDTO UtenteToUtenteDTO(UtenteDTO utente) {
        if (utente == null) {
            return null;
        }

        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setCodiceFiscale(utente.getCodiceFiscale());
        utenteDTO.setComuneDiNascita(utente.getComuneDiNascita());
        utenteDTO.setDataNascita(utente.getDataNascita());
        utenteDTO.setProvincia(utente.getProvincia());
        utenteDTO.setComuneDiResidenza(utente.getComuneDiResidenza());
        utenteDTO.setVia(utente.getVia());
        utenteDTO.setNumeroCivico(utente.getNumeroCivico());
        utenteDTO.setCap(utente.getCap());
        utenteDTO.setSesso(utente.getSesso());
        return utenteDTO;
    }

    private UtenteDTO UtenteDTOToUtente(UtenteDTO utenteDTO) {
        UtenteDTO utente = new UtenteDTO();
        utente.setId(utenteDTO.getId());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setCodiceFiscale(utenteDTO.getCodiceFiscale());
        utente.setComuneDiNascita(utenteDTO.getComuneDiNascita());
        utente.setDataNascita(utenteDTO.getDataNascita());
        utente.setProvincia(utenteDTO.getProvincia());
        utente.setComuneDiResidenza(utenteDTO.getComuneDiResidenza());
        utente.setVia(utenteDTO.getVia());
        utente.setNumeroCivico(utenteDTO.getNumeroCivico());
        utente.setCap(utenteDTO.getCap());
        utente.setSesso(utenteDTO.getSesso());
        return utente;
    }
}
