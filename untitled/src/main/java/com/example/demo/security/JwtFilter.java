package com.example.demo.security;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "minhaChaveSecreta"; // Use um segredo mais forte na produção

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Recupera o token do cabeçalho Authorization
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " do começo

            try {
                // Usando o metodo parserBuilder() para compatibilidade com a versão 0.12.3
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Adiciona o nome de usuário ao contexto da requisição (opcional)
                String username = claims.getSubject();
                request.setAttribute("user", username);
            } catch (SignatureException e) {
                // Se o token for inválido, responde com 401 Unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            } catch (JwtException e) {
                // Trata outros erros (como expiração do token)
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Erro ao validar o token");
                return;
            }
        }

        // Continua a execução da cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
