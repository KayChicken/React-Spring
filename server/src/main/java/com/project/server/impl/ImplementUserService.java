package com.project.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.server.enums.Role;
import com.project.server.model.User;
import com.project.server.repository.UserRepository;
import com.project.server.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImplementUserService implements UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

}
