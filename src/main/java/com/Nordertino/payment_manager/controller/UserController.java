package com.Nordertino.payment_manager.controller;

import com.Nordertino.payment_manager.domain.User;
import com.Nordertino.payment_manager.repositories.UserRepository;
import com.Nordertino.payment_manager.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest data){
        try {
            User user = new User(data);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            User savedUser = repository.save(user);

            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}
