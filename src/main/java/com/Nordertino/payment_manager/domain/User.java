package com.Nordertino.payment_manager.domain;

import com.Nordertino.payment_manager.request.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Autowired
    private String name;
    @Autowired
    private String email;
    @Autowired
    private String password;
    @Autowired
    private LocalDateTime createdAt;
    @Autowired
    private LocalDateTime updatedAt;

    public User(UserRequest request) {
        this.id = request.id();
        this.name = request.name();
        this.email = request.email();
        this.password = request.password();
        this.createdAt = request.createdAt();
        this.updatedAt = request.updatedAt();
    }
}
