package com.example.demo.DTOLogin;

public class LoginDTO {
    private String email;
    private String password;

    // Construtor padrão
    public LoginDTO() {}

    // Construtor com parâmetros
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter para email
    public String getEmail() {
        return email;
    }

    // Setter para email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter para password
    public String getPassword() {
        return password;
    }

    // Setter para password
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
