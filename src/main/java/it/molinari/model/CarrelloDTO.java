package it.molinari.model;

import java.util.List;

public class CarrelloDTO {
    private Integer id;
    private String utenteId; // Assumendo che utenteId sia una stringa, ad es. codice fiscale
    private List<ItemCarrello> items;

    // Getters e Setters

    public static class ItemCarrello {
        private Integer prodottoId;
        private Integer quantita;
		public Integer getProdottoId() {
			return prodottoId;
		}
		public void setProdottoId(Integer prodottoId) {
			this.prodottoId = prodottoId;
		}
		public Integer getQuantita() {
			return quantita;
		}
		public void setQuantita(Integer quantita) {
			this.quantita = quantita;
		}

        // Getters e Setters
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(String utenteId) {
		this.utenteId = utenteId;
	}

	public List<ItemCarrello> getItems() {
		return items;
	}

	public void setItems(List<ItemCarrello> items) {
		this.items = items;
	}
	@Override
	public String toString() {
	    return "CarrelloDTO [id=" + id +
	           ", utenteId=" + utenteId +
	           ", items=" + items.toString() + "]";
	}

}
