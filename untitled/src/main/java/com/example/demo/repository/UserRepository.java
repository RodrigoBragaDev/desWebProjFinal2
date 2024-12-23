package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    // Buscar todos os usuários que possuem reservas (usando JPQL)
    @Query("SELECT u FROM User u JOIN u.reservas r WHERE r IS NOT NULL")
    List<User> findUsersWithReservations();
}
