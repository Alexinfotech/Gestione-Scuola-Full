package it.molinari.model;

public class CarrelloDTO {
    private Integer id;
    private Integer loginId;
    private Integer prodottoId;
    private Integer quantita;  // Usare Integer per quantita può essere più appropriato

    // Costruttori, getter e setter qui sotto
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

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

    @Override
    public String toString() {
        return "CarrelloDTO [id=" + id + ", loginId=" + loginId + ", prodottoId=" + prodottoId + ", quantita=" + quantita + "]";
    }
}
