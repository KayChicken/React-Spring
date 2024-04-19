package com.project.server.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.server.model.Token;
import com.project.server.model.User;
import com.project.server.service.JwtService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private JwtService service;

    @PostMapping
    public ResponseEntity isAuth(@RequestBody Token token) {
        String username = service.extractUserName(token.getToken());
        if (username == null || username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь не найден");
        }

        return ResponseEntity.ok(username);
    }
}
