package it.molinari.service;

import it.molinari.DAO.UtentiDAO;
import it.molinari.DAO.Utente;
import it.molinari.DTO.UtenteDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UtenteService {

    private UtentiDAO gestioneUtenti;

    public UtenteService() {
        this.gestioneUtenti = new UtentiDAO();
    }

    public UtenteDTO getUtente(String codiceFiscale) throws SQLException{
        Utente utente = gestioneUtenti.getUtente(codiceFiscale);
        return UtenteToUtenteDTO(utente);
    }

    public void inserisciUtente(UtenteDTO utenteDTO) throws SQLException, ClassNotFoundException{
        Utente utente = UtenteDTOToUtente(utenteDTO);
        gestioneUtenti.inserisciUtente(utente);
    }

    public void aggiornaUtente(UtenteDTO utenteDTO) throws SQLException{
        Utente utente = UtenteDTOToUtente(utenteDTO);
        gestioneUtenti.updateUtente(utente);
    }

    public void eliminaUtente(String codiceFiscale) throws SQLException{
        gestioneUtenti.deleteUtente(codiceFiscale);
    }

    public List<UtenteDTO> ottieniTuttiGliUtenti() throws ClassNotFoundException, SQLException{
        List<Utente> listaUtenti = gestioneUtenti.recuperaUtenti();
        List<UtenteDTO> listaUtentiDTO = new ArrayList<>();
        for (Utente utente : listaUtenti) {
            UtenteDTO dto = UtenteToUtenteDTO(utente); // Usa il metodo corretto
            listaUtentiDTO.add(dto);
           
        }
        return listaUtentiDTO;
    }

    private UtenteDTO UtenteToUtenteDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.setId(utente.getId());
        utenteDTO.setNome(utente.getNome());
        utenteDTO.setCognome(utente.getCognome());
        utenteDTO.setEmail(utente.getEmail());
        utenteDTO.setCodiceFiscale(utente.getCodiceFiscale());
        if (utente.getDataNascita() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            utenteDTO.setDataNascita(sdf.format(utente.getDataNascita()));
        }        utenteDTO.setComuneDiNascita(utente.getComuneDiNascita());
        utenteDTO.setProvincia(utente.getProvincia());
        utenteDTO.setComuneDiResidenza(utente.getComuneDiResidenza());
        utenteDTO.setVia(utente.getVia());
        utenteDTO.setNumeroCivico(utente.getNumeroCivico());
        utenteDTO.setCap(utente.getCap());
        utenteDTO.setSesso(utente.getSesso());
        return utenteDTO;
    }

    private Utente UtenteDTOToUtente(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setId(utenteDTO.getId());
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setCodiceFiscale(utenteDTO.getCodiceFiscale());
        if (utenteDTO.getDataNascita() != null && !utenteDTO.getDataNascita().isEmpty()) {
            try {
                Date dataNascita = Date.valueOf(utenteDTO.getDataNascita());
                utente.setDataNascita(dataNascita);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        utente.setComuneDiNascita(utenteDTO.getComuneDiNascita());
        utente.setProvincia(utenteDTO.getProvincia());
        utente.setComuneDiResidenza(utenteDTO.getComuneDiResidenza());
        utente.setVia(utenteDTO.getVia());
        utente.setNumeroCivico(utenteDTO.getNumeroCivico());
        utente.setCap(utenteDTO.getCap());
        utente.setSesso(utenteDTO.getSesso());
        return utente;
    }
}
