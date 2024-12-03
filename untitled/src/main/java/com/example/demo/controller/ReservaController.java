package com.example.demo.controller;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.entity.Reserva;
import com.example.demo.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Buscar todas as reservas
    @GetMapping
    public List<ReservaDTO> getAllReservas() {
        return reservaService.findAllReservas()
                .stream()
                .map(ReservaDTO::new) // Convertendo para DTO
                .collect(Collectors.toList());
    }

    // Buscar uma reserva pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        return reservaService.findReservaById(id)
                .map(reserva -> new ResponseEntity<>(new ReservaDTO(reserva), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova reserva
    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody Reserva reserva) {
        Reserva newReserva = reservaService.saveReserva(reserva);
        return new ResponseEntity<>(new ReservaDTO(newReserva), HttpStatus.CREATED);
    }

    // Deletar uma reserva pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar reservas por ID do usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<ReservaDTO> getReservasByUsuarioId(@PathVariable Long usuarioId) {
        return reservaService.findByUsuarioId(usuarioId)
                .stream()
                .map(ReservaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar reservas por ID do local esportivo
    @GetMapping("/local/{localEsportesId}")
    public List<ReservaDTO> getReservasByLocalEsportesId(@PathVariable Long localEsportesId) {
        return reservaService.findByLocalEsportesId(localEsportesId)
                .stream()
                .map(ReservaDTO::new)
                .collect(Collectors.toList());
    }
}




