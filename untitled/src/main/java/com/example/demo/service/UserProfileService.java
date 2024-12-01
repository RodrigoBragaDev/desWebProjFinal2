package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Buscar todos os perfis de usuário
    public List<UserProfile> findAllUserProfile() {
        return userProfileRepository.findAll();
    }

    // Buscar perfil de usuário por ID
    public Optional<UserProfile> findUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    // Salvar um novo perfil de usuário
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    // Excluir perfil de usuário por ID
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}
