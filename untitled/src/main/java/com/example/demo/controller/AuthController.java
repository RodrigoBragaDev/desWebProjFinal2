package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            return JwtTokenUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }

    // Classe LoginRequest dentro do AuthController
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters e Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
