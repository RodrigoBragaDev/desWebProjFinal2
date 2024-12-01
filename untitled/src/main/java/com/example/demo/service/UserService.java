package com.example.demo.service;

import com.example.demo.entity.Reserva;
import com.example.demo.entity.LocalEsportes;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.repository.LocalEsportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LocalEsportesRepository localEsportesRepository;

    // Buscar todos os usuários
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Buscar um usuário por ID
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Salvar um novo usuário
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Atualizar um usuário existente
    public User updateUser(Long id, User updateUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateUser.getName());
                    user.setEmail(updateUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    // Excluir um usuário
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Buscar todos os locais esportivos de um usuário (Proprietário)
    //public List<LocalEsportes> findLocalEsportesByUser(Long userId) {
    //   User user = userRepository.findById(userId)
    //            .orElseThrow(() -> new RuntimeException("User not found"));
    //    return localEsportesRepository.findByDono(user);
    //}

    // Buscar todas as reservas de um usuário
    public List<Reserva> findReservasByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reservaRepository.findByUser(user);
    }

    // Adicionar uma nova reserva para um usuário
    public Reserva addReservaToUser(Long userId, Long localEsportesId, Reserva reserva) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalEsportes localEsportes = localEsportesRepository.findById(localEsportesId)
                .orElseThrow(() -> new RuntimeException("LocalEsportes not found"));

        reserva.setUser(user);
        reserva.setLocalEsportes(localEsportes);

        return reservaRepository.save(reserva);
    }

    // Buscar usuários que possuem reservas
    public List<User> findUsersWithReservations() {
        return userRepository.findUsersWithReservations();
    }
}
