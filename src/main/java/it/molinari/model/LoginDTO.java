package it.molinari.model;

public class LoginDTO {
	private String email;
	private String password;
	private Integer idUtente;
	private String ruolo; // Aggiungi questa linea

	public LoginDTO() {
	}

	// Costruttore con parametri
	public LoginDTO(String emailLogin, String password) {

		this.password = password;
	}

	public String getEmailLogin() {
		return email;
	}

	public void setEmailLogin(String emailLogin) {
		this.email = emailLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + ", idUtente=" + idUtente + ", ruolo=" + ruolo
				+ "]";
	}

}
