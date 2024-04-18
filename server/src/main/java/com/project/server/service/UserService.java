package com.project.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.project.server.model.User;

@Service
public interface UserService {
    User getByUsername(String username);

    User save(User user);

    User create(User user);

    UserDetailsService userDetailsService();

    User getCurrentUser();

    void getAdmin();

}
