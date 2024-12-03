package com.example.demo.dto;

import com.example.demo.entity.LocalEsportes;
import com.example.demo.entity.Reserva;

import java.util.List;
import java.util.stream.Collectors;

public class LocalEsportesDTO {

    private Long id;
    private String nome;
    private String endereco;
    private List<ReservaDTO> reservas; // Lista de reservas no formato DTO

    // Construtor que converte a entidade LocalEsportes para DTO
    public LocalEsportesDTO(LocalEsportes localEsportes) {
        this.id = localEsportes.getId();
        this.nome = localEsportes.getNome();
        this.endereco = localEsportes.getEndereco();
        this.reservas = localEsportes.getReservas().stream()
                .map(ReservaDTO::new) // Convertendo cada reserva para ReservaDTO
                .collect(Collectors.toList());
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
