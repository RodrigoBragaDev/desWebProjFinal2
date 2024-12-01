package com.example.demo.controller;

import com.example.demo.entity.Reserva;
import com.example.demo.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Buscar todas as reservas
    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.findAllReservas();
    }

    // Buscar uma reserva pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.findReservaById(id)
                .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Criar nova reserva
    //@PostMapping
    //public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
    //    Reserva newReserva = reservaService.saveReserva(reserva);
    //    return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    //}

    // Criar uma nova reserva
    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.saveReserva(reserva);
    }

    // Deletar uma reserva pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar reservas por ID do usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> getReservasByUsuarioId(@PathVariable Long usuarioId) {
        return reservaService.findByUsuarioId(usuarioId);
    }

    // Buscar reservas por ID do local esportivo
    @GetMapping("/local/{localEsportesId}")
    public List<Reserva> getReservasByLocalEsportesId(@PathVariable Long localEsportesId) {
        return reservaService.findByLocalEsportesId(localEsportesId);
    }
}



