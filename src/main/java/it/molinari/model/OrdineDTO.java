package it.molinari.model;

import java.util.HashMap;
import java.util.Map;

public class OrdineDTO {
	private Integer idOrdine;
	private String dataOrdine;
	private Double costoTotale;
	private Map<String, Double> dettagliProdotti = new HashMap<>(); // Memorizza i dettagli dei prodotti per l'ordine

	public Integer getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(Double costoTotale) {
		this.costoTotale = costoTotale;
	}

	// Getter per i dettagli dei prodotti
	public Map<String, Double> getDettagliProdotti() {
		return dettagliProdotti;
	}

	// Metodo per aggiungere un dettaglio prodotto
	public void addDettaglioProdotto(String nomeProdotto, Double prezzo) {
		dettagliProdotti.put(nomeProdotto, prezzo);
	}

	@Override
	public String toString() {
		return "OrdineDTO [idOrdine=" + idOrdine + ", dataOrdine=" + dataOrdine + ", costoTotale=" + costoTotale
				+ ", dettagliProdotti=" + dettagliProdotti + "]";
	}
}
