package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "minhaChaveSecreta"; // Use um segredo mais forte na produção

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de validade
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Metodo para validar o token pode ser adicionado aqui, se necessário
}
