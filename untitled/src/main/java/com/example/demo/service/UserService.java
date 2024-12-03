package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Buscar todos os usuários
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Buscar um usuário pelo ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Criar ou atualizar um usuário
    public User saveUser(User user) {
        // Verifica se a role do usuário é null e define como "usuário"
        if (user.getRole() == null) {
            user.setRole("USUARIO");
        }
        return userRepository.save(user);
    }

    // Atualizar um usuário existente
    public User updateUser(Long id, User updateUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setEmail(updateUser.getEmail());

                    // Atualizar a senha, se fornecida
                    if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                        user.setPassword(updateUser.getPassword()); // Apenas atualize se a senha não for nula ou vazia
                    }

                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    // Deletar um usuário pelo ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Buscar usuários que possuem reservas
    public List<User> findUsersWithReservations() {
        return userRepository.findUsersWithReservations();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
