package com.example.demo.service;

import com.example.demo.entity.LocalEsportes;
import com.example.demo.repository.LocalEsportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalEsportesService {

    @Autowired
    private LocalEsportesRepository localEsportesRepository;

    // Buscar todos os locais esportivos
    public List<LocalEsportes> findAllLocalEsportes() {
        return localEsportesRepository.findAll();
    }

    // Buscar um local esportivo pelo ID
    public Optional<LocalEsportes> findLocalEsportesById(Long id) {
        return localEsportesRepository.findById(id);
    }

    // Criar ou atualizar um local esportivo
    public LocalEsportes saveLocalEsportes(LocalEsportes localEsportes) {
        return localEsportesRepository.save(localEsportes);
    }

    // Deletar um local esportivo pelo ID
    public void deleteLocalEsportes(Long id) {
        localEsportesRepository.deleteById(id);
    }

    // Buscar locais esportivos por ID do dono
    public List<LocalEsportes> findByDonoId(Long donoId) {
        return localEsportesRepository.findByDonoId(donoId);
    }
}
