package it.molinari.model;

import it.molinari.model.ProdottoDTO;

public class ProdottoDTO {
	private Integer id;
	private String nomeProdotto;
	private String prezzo; // Modifica il tipo di dato del prezzo a String
	private String iva; // Modifica il tipo di dato dell'IVA a String
	private String descrizioneProdotto;
	private String quantita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public String getPrezzo() { // Modifica il tipo di dato restituito a String
		return prezzo;
	}

	public void setPrezzo(String prezzo) { // Modifica il tipo di dato del parametro a String
		this.prezzo = prezzo;
	}

	public String getIva() { // Modifica il tipo di dato restituito a String
		return iva;
	}

	public void setIva(String iva) { // Modifica il tipo di dato del parametro a String
		this.iva = iva;
	}

	public String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}

	public void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}

	public String getQuantita() {
		return quantita;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "ProdottoDTO [id=" + id + ", nomeProdotto=" + nomeProdotto + ", prezzo=" + prezzo + ", iva=" + iva
				+ ", descrizioneProdotto=" + descrizioneProdotto + ", quntita=" + quantita + " ]";
	}

}
