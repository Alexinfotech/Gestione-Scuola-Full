package it.molinari.model;

import java.util.Date;


public class UtenteDTO {
	private Integer id;
	private String nome;
	private String cognome;
	private String email;
	private String codiceFiscale;
	private Date dataNascita;
	private String comuneDiNascita;
	private String provincia;
	private String comuneDiResidenza;
	private String via;
	private String numeroCivico;
	private String cap;
	private String sesso;
    private static String apiToken = "120d80c4786d1f557e2ca6b3dc2a487b507c99236d8cf1666e39164369d84777556";


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	public String getComuneDiResidenza() {
		return comuneDiResidenza;
	}

	public void setComuneDiResidenza(String comuneDiResidenza) {
		this.comuneDiResidenza = comuneDiResidenza;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
    public static String getApiToken() {
        return apiToken;
    }

    public static void setApiToken(String token) {
    	apiToken = token;
    }
	@Override
	public String toString() {
		return "UtenteDTO [id=" + id + ",codiceFiscale=" + codiceFiscale + ", nome=" + nome + ", cognome=" + cognome
				+ ",sesso=" + sesso + ", email=" + email + ",  dataNascita=" + dataNascita + ", comuneDiNascita="
				+ comuneDiNascita + ", comuneDiResidenza=" + comuneDiResidenza + ",provincia=" + provincia + ", via="
				+ via + ", numeroCivico=" + numeroCivico + ", cap=" + cap + "]";
	}

}
