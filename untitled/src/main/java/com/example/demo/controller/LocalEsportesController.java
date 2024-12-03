package com.example.demo.controller;

import com.example.demo.entity.LocalEsportes;
import com.example.demo.service.LocalEsportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

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
    public ResponseEntity<List<LocalEsportes>> getAllLocalEsportes() {
        List<LocalEsportes> locais = localEsportesService.findAllLocalEsportes();
        
        if (locais.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        // Validação dos campos obrigatórios
        for (LocalEsportes local : locais) {
            if (local.getNome() == null || local.getEndereco() == null) {
                // Log para debug
                System.out.println("Local com dados incompletos: ID=" + local.getId() + 
                                 ", Nome=" + local.getNome() + 
                                 ", Endereco=" + local.getEndereco());
            }
        }
        
        return new ResponseEntity<>(locais, HttpStatus.OK);
    }

    // Buscar um local esportivo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<LocalEsportes> getLocalEsportesById(@PathVariable Long id) {
        return localEsportesService.findLocalEsportesById(id)
                .map(local -> new ResponseEntity<>(local, HttpStatus.OK))
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

    // Buscar locais esportivos por ID do dono
    @GetMapping("/dono/{donoId}")
    public List<LocalEsportes> getLocalEsportesByDonoId(@PathVariable Long donoId) {
        return localEsportesService.findByDonoId(donoId);
    }
}