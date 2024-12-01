package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "local_esportes_id")
    private LocalEsportes localEsportes;

    private LocalDateTime dataHora;

    private String status;

    // Definindo constantes de status de reserva
    public static final String STATUS_ATIVA = "ATIVA";
    public static final String STATUS_CANCELADA = "CANCELADA";
    public static final String STATUS_CONCLUIDA = "CONCLUÍDA";

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalEsportes getLocalEsportes() {
        return localEsportes;
    }

    public void setLocalEsportes(LocalEsportes localEsportes) {
        this.localEsportes = localEsportes;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
