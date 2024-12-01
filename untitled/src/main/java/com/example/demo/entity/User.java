package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private Set<LocalEsportes> locaisEsportes = new HashSet<>();

    // Definindo as constantes de papel de usuário
    public static final String ROLE_VISITOR = "VISITOR";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_HOST = "HOST";

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<LocalEsportes> getLocaisEsportes() {
        return locaisEsportes;
    }

    public void setLocaisEsportes(Set<LocalEsportes> locaisEsportes) {
        this.locaisEsportes = locaisEsportes;
    }
}
