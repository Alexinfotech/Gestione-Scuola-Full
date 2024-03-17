package it.molinari.model;

public class RuoloDTO {
	private String ruolo;

	public RuoloDTO() {
	}

	public RuoloDTO(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "RuoloDTO [ruolo=" + ruolo + "]";
	}
}
