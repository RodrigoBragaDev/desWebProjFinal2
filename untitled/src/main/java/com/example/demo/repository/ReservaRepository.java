package com.example.demo.repository;

import com.example.demo.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Buscar reservas por ID do usuário
    List<Reserva> findByUsuarioId(Long usuarioId);

    // Buscar reservas por ID do local esportivo
    List<Reserva> findByLocalEsportesId(Long localEsportesId);
}
