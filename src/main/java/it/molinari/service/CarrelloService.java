package it.molinari.service;

import java.sql.SQLException;
import java.util.List;

import it.molinari.DAO.CarrelloDAO;
import it.molinari.model.CarrelloDTO;
import it.molinari.model.ProdottoDTO;

public class CarrelloService {

	private CarrelloDAO gestioneCarrello;

	public CarrelloService() {
		this.gestioneCarrello = new CarrelloDAO();
	}

	// Aggiungi un prodotto al carrello
	public void aggiungiAlCarrello(int loginId, int prodottoId, int quantita) throws SQLException {
		// Potresti voler verificare prima che la quantità richiesta sia disponibile
		gestioneCarrello.create(loginId, prodottoId, quantita);
		// Aggiorna la quantità disponibile del prodotto
		// Questo richiederà una logica aggiuntiva per interagire con ProdottoDAO
	}

	// Rimuovi un prodotto dal carrello
	public void rimuoviDalCarrello(String idCarrello) throws SQLException {
		gestioneCarrello.delete(idCarrello);
	}

	// Aggiorna la quantità di un prodotto nel carrello
	public void aggiornaQuantita(String idCarrello, int nuovaQuantita) throws SQLException {
		// Implementazione del metodo per aggiornare la quantità
	}

	// Recupera il carrello di un utente
	public List<CarrelloDTO> recupera(int loginId) throws SQLException {
		// Implementazione del metodo per recuperare il carrello di un utente specifico
		return null;
	}
}
