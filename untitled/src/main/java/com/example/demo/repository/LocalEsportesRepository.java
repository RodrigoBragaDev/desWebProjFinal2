package com.example.demo.repository;

import com.example.demo.entity.LocalEsportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalEsportesRepository extends JpaRepository<LocalEsportes, Long> {
    // Buscar locais por dono
    List<LocalEsportes> findByDonoId(Long donoId);
}
