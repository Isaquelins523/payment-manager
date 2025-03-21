package com.Nordertino.payment_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Autowired
    private String transactionId;
    @Autowired
    private Double amount;
    @Autowired
    private String description;
    @Autowired
    private String email;
    @Autowired
    private String status;
    @Autowired
    private LocalDateTime createdAt;
    @Autowired
    private LocalDateTime updatedAt;
    @Autowired
    private String paymentMethodId;


    public Payment(Long id, String transactionId, Double amount, String description, String email, String status, LocalDateTime createdAt, LocalDateTime updatedAt, String paymentMethodId) {
        this.id = id;
        this.transactionId = transactionId;
        this.amount = amount;
        this.description = description;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.paymentMethodId = paymentMethodId;
    }
}
