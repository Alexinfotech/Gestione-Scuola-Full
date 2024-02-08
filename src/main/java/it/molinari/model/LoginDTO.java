package it.molinari.model;

public class LoginDTO {
    private String email;
    private String password;

    // Costruttore vuoto se necessario
    public LoginDTO() {
    }

    // Costruttore con parametri
    public LoginDTO(String emailLogin, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter e Setter
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

    // Metodo toString per rappresentare l'oggetto sotto forma di stringa
    @Override
    public String toString() {
        return "LoginDTO [email=" + email + ", password=" + password + "]";
    }
}
