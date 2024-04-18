package com.project.server.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.server.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
