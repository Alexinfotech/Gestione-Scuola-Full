package it.molinari.DTO;

public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO() {
    }

    // Costruttore con parametri
    public LoginDTO(String emailLogin, String password) {
        this.email = email;
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

    @Override
    public String toString() {
        return "LoginDTO [email=" + email + ", password=" + password + "]";
    }
}
