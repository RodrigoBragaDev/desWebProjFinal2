package com.example.demo.service;

import com.example.demo.entity.Reserva;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Buscar todas as reservas
    public List<Reserva> findAllReservas() {
        return reservaRepository.findAll();
    }

    // Buscar uma reserva pelo ID
    public Optional<Reserva> findReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    // Criar ou atualizar uma reserva
    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Deletar uma reserva pelo ID
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public Reserva updateReserva(Long id, Reserva updatedReserva) {
        // Verificar se a reserva existe
        Reserva existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));

        // Atualizar os campos se eles forem fornecidos
        if (updatedReserva.getDataHora() != null) {
            existingReserva.setDataHora(updatedReserva.getDataHora());
        }
        if (updatedReserva.getLocalEsportes() != null) {
            existingReserva.setLocalEsportes(updatedReserva.getLocalEsportes());
        }
        if (updatedReserva.getUser() != null) {
            existingReserva.setUser(updatedReserva.getUser());
        }
        if (updatedReserva.getStatus() != null) {
            existingReserva.setStatus(updatedReserva.getStatus());
        }

        return reservaRepository.save(existingReserva);
    }

    // Buscar reservas por ID do usuário
    public List<Reserva> findByUsuarioId(Long userId) {
        return reservaRepository.findByUser_Id(userId);
    }

    // Buscar reservas por ID do local esportivo
    public List<Reserva> findByLocalEsportesId(Long localEsportesId) {
        return reservaRepository.findByLocalEsportesId(localEsportesId);
    }
}
