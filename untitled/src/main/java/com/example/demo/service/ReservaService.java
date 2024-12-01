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

    // Buscar reservas por ID do usuário
    public List<Reserva> findByUsuarioId(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    // Buscar reservas por ID do local esportivo
    public List<Reserva> findByLocalEsportesId(Long localEsportesId) {
        return reservaRepository.findByLocalEsportesId(localEsportesId);
    }
}
