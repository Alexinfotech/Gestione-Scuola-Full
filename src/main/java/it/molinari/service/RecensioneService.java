package it.molinari.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.molinari.DAO.RecensioneDAO;

import it.molinari.model.RecensioneDTO;

public class RecensioneService {

	private RecensioneDAO gestioneRecensione;

	public RecensioneService() {
		this.gestioneRecensione = new RecensioneDAO();
	}

	public RecensioneDTO get(String id) throws SQLException {
		RecensioneDTO recensione = gestioneRecensione.get(id);
		return RecensioneToRecensioneDTO(recensione);
	}

	public void create(RecensioneDTO recensioneDTO) throws SQLException {
		RecensioneDTO recensione = RecensioneDTOToRecensione(recensioneDTO);
		gestioneRecensione.create(recensione);
	}

	public void update(RecensioneDTO recensioneDTO) throws SQLException {
		RecensioneDTO recensione = RecensioneDTOToRecensione(recensioneDTO);
		gestioneRecensione.update(recensione);
	}

	public void delete(String id) throws SQLException {
		gestioneRecensione.delete(id);
	}

	public List<RecensioneDTO> recuperaRecensioneProdotto(String id) throws SQLException {
		List<RecensioneDTO> listaRecensioneProdotto = gestioneRecensione.recuperaRecensioneProdotto(id);
		List<RecensioneDTO> listaRecensioneProdottoDTO = new ArrayList<>();
		for (RecensioneDTO recensione : listaRecensioneProdotto) {
			RecensioneDTO dto = RecensioneToRecensioneDTO(recensione);
			listaRecensioneProdottoDTO.add(dto);
		}
		return listaRecensioneProdottoDTO;
	}

	public List<RecensioneDTO> recupera() throws ClassNotFoundException, SQLException {
		List<RecensioneDTO> listaRecensione = gestioneRecensione.recupera();
		List<RecensioneDTO> listaRecensioneDTO = new ArrayList<>();
		for (RecensioneDTO recensione : listaRecensione) {
			RecensioneDTO dto = RecensioneToRecensioneDTO(recensione);
			listaRecensioneDTO.add(dto);
		}
		return listaRecensioneDTO;
	}

	private RecensioneDTO RecensioneToRecensioneDTO(RecensioneDTO recensione) {
		if (recensione == null) {
			return null;
		}
		RecensioneDTO recensioneDTO = new RecensioneDTO();
		recensioneDTO.setId(recensione.getId());
		recensioneDTO.setProdottoId(recensione.getProdottoId());
		recensioneDTO.setTesto(recensione.getTesto());

		return recensioneDTO;
	}

	private RecensioneDTO RecensioneDTOToRecensione(RecensioneDTO recensioneDTO) {
		RecensioneDTO recensione = new RecensioneDTO();
		recensione.setId(recensioneDTO.getId());
		recensione.setProdottoId(recensioneDTO.getProdottoId());
		recensione.setTesto(recensioneDTO.getTesto());
		return recensione;
	}
}