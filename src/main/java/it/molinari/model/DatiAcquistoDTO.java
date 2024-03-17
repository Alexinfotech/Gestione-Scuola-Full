package it.molinari.model;

public class DatiAcquistoDTO {
	private UtenteDTO utente;
	private ProdottoDTO prodotto;

	// Getters e setters
	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public ProdottoDTO getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoDTO prodotto) {
		this.prodotto = prodotto;
	}
}
