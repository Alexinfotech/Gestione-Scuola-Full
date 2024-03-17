package it.molinari.model;

public class RecensioneDTO {
	private Integer id;
	private Integer prodottoId;
	private String testo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProdottoId() {
		return prodottoId;
	}

	public void setProdottoId(Integer prodottoId) {
		this.prodottoId = prodottoId;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public String toString() {
		return "RecensioneDTO [id=" + id + ", prodottoId=" + prodottoId + ", testo='" + testo + "']";
	}
}
