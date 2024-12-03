package com.example.demo.service;

import com.example.demo.entity.LocalEsportes;
import com.example.demo.entity.User;
import com.example.demo.repository.LocalEsportesRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalEsportesService {

    @Autowired
    private LocalEsportesRepository localEsportesRepository;
    private UserRepository userRepository;

    public LocalEsportesService(LocalEsportesRepository localEsportesRepository, UserRepository userRepository) {
        this.localEsportesRepository = localEsportesRepository;
        this.userRepository = userRepository;
    }

    // Buscar todos os locais esportivos
    public List<LocalEsportes> findAllLocalEsportes() {
        return localEsportesRepository.findAll();
    }

    // Buscar um local esportivo pelo ID
    public Optional<LocalEsportes> findLocalEsportesById(Long id) {
        return localEsportesRepository.findById(id);
    }

    /*// Criar ou atualizar um local esportivo
    public LocalEsportes saveLocalEsportes(LocalEsportes localEsportes) {
        return localEsportesRepository.save(localEsportes);
    }*/

    public LocalEsportes saveLocalEsportes(LocalEsportes localEsportes) {
        // Salvar o LocalEsportes
        LocalEsportes savedLocal = localEsportesRepository.save(localEsportes);

        // Recuperar o dono associado
        User dono = localEsportes.getDono();
        if (dono != null) {
            // Carregar o usuário completo do banco
            User completo = userRepository.findById(dono.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Dono não encontrado no banco de dados"));

            // Verificar e atualizar a ROLE se necessário
            if (!"DONO".equals(completo.getRole())) {
                completo.setRole("DONO");
                userRepository.save(completo); // Salvar o usuário com a ROLE atualizada
            }
        }

        return savedLocal;
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
