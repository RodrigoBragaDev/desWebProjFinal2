package com.example.demo.controller;

import com.example.demo.dto.LocalEsportesDTO;
import com.example.demo.entity.LocalEsportes;
import com.example.demo.service.LocalEsportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/local-esportes")
@CrossOrigin(origins = "http://localhost:3000")
public class LocalEsportesController {

    @Autowired
    private LocalEsportesService localEsportesService;

    public LocalEsportesController(LocalEsportesService localEsportesService) {
        this.localEsportesService = localEsportesService;
    }

    // Buscar todos os locais esportivos
    @GetMapping
    public ResponseEntity<List<LocalEsportesDTO>> getAllLocalEsportes() {
        List<LocalEsportes> locais = localEsportesService.findAllLocalEsportes();

        if (locais.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Convertendo para DTO
        List<LocalEsportesDTO> locaisDTO = locais.stream()
                .map(LocalEsportesDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(locaisDTO, HttpStatus.OK);
    }

    // Buscar um local esportivo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<LocalEsportesDTO> getLocalEsportesById(@PathVariable Long id) {
        return localEsportesService.findLocalEsportesById(id)
                .map(local -> new ResponseEntity<>(new LocalEsportesDTO(local), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo local esportivo
    @PostMapping
    public ResponseEntity<LocalEsportes> createLocalEsportes(@RequestBody LocalEsportes localEsportes) {
        // Validação dos campos obrigatórios
        if (localEsportes.getNome() == null || localEsportes.getNome().trim().isEmpty() ||
                localEsportes.getEndereco() == null || localEsportes.getEndereco().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LocalEsportes savedLocal = localEsportesService.saveLocalEsportes(localEsportes);
        return new ResponseEntity<>(savedLocal, HttpStatus.CREATED);
    }

    // Deletar um local esportivo pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalEsportes(@PathVariable Long id) {
        localEsportesService.deleteLocalEsportes(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Atualizar um local esportivo pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<LocalEsportes> updateLocalEsportes(@PathVariable Long id, @RequestBody LocalEsportes updatedLocalEsportes) {
        try {
            LocalEsportes updatedLocal = localEsportesService.updateLocalEsportes(id, updatedLocalEsportes);
            return new ResponseEntity<>(updatedLocal, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Buscar locais esportivos por ID do dono
    @GetMapping("/dono/{donoId}")
    public List<LocalEsportesDTO> getLocalEsportesByDonoId(@PathVariable Long donoId) {
        return localEsportesService.findByDonoId(donoId)
                .stream()
                .map(LocalEsportesDTO::new)
                .collect(Collectors.toList());
    }
}
