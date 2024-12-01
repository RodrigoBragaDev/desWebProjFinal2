package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    // Buscar todos os perfis de usuários
    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.findAllUserProfile();
    }

    // Buscar perfil de usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        return userProfileService.findUserProfileById(id)
                .map(userProfile -> new ResponseEntity<>(userProfile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo perfil de usuário
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile newUserProfile = userProfileService.saveUserProfile(userProfile);
        return new ResponseEntity<>(newUserProfile, HttpStatus.CREATED);
    }

    // Atualizar um perfil de usuário
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long id, @RequestBody UserProfile userProfile) {
        try {
            UserProfile updatedUserProfile = userProfileService.saveUserProfile(userProfile);
            return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir um perfil de usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
