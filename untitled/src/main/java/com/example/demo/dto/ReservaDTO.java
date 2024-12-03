package com.example.demo.dto;

import com.example.demo.entity.LocalEsportes;
import com.example.demo.entity.Reserva;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ReservaDTO {

    private Long id;
    private Long localEsportesId;
    private String localEsportesNome;
    private Long userId;
    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Formatação do campo dataHora
    private LocalDateTime dataHora;

    // Construtor que converte uma entidade Reserva para DTO
    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();

        LocalEsportes localEsportes = reserva.getLocalEsportes();
        if (localEsportes != null) {
            this.localEsportesId = localEsportes.getId();
            this.localEsportesNome = localEsportes.getNome();
        }

        User user = reserva.getUser();
        if (user != null) {
            this.userId = user.getId();
            this.userName = user.getName();
        }

        this.dataHora = reserva.getDataHora();  // Adicionando a dataHora ao DTO
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocalEsportesId() {
        return localEsportesId;
    }

    public void setLocalEsportesId(Long localEsportesId) {
        this.localEsportesId = localEsportesId;
    }

    public String getLocalEsportesNome() {
        return localEsportesNome;
    }

    public void setLocalEsportesNome(String localEsportesNome) {
        this.localEsportesNome = localEsportesNome;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
