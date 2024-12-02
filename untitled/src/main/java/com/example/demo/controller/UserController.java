package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    // Buscar todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Buscar um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar um novo usuário e gerar um token JWT
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Erro: A senha não pode estar vazia.");
        }
        try {
            // Salva o usuário no banco de dados
            User savedUser = userService.saveUser(user);

            // Gera o token JWT para o usuário criado
            String token = JwtTokenUtil.generateToken(savedUser.getEmail());

            // Retorna o usuário criado junto com o token JWT
            return ResponseEntity.ok().body(new UserResponse(savedUser, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o usuário.");
        }
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar usuários com reservas
    @GetMapping("/with-reservations")
    public List<User> getUsersWithReservations() {
        return userService.findUsersWithReservations();
    }

    // Classe auxiliar para a resposta com usuário e token JWT
    public static class UserResponse {
        private User user;
        private String token;

        public UserResponse(User user, String token) {
            this.user = user;
            this.token = token;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
