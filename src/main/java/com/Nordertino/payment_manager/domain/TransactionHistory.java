package com.Nordertino.payment_manager.domain;

import com.Nordertino.payment_manager.request.TransactionsHistoryRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String payment_method;

    private String description;

    private LocalDateTime transaction_date;

    private String status; // Pode ser "pending", "approved", "failed"

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Construtor para usar o TransactionsHistoryRequest
    public TransactionHistory(TransactionsHistoryRequest request) {
        this.id = request.id();
        this.amount = request.amount();
        this.payment_method = request.payment_method();
        this.description = request.description();
        this.transaction_date = request.transaction_date();
        this.user = request.user();
        this.status = request.status();
    }

    // Construtor com parâmetros que você usa para salvar a transação
    public TransactionHistory(Long id, BigDecimal amount, String paymentMethod, String description, LocalDateTime transactionDate, User user, String status) {
        this.id = id;
        this.amount = amount;
        this.payment_method = paymentMethod;
        this.description = description;
        this.transaction_date = transactionDate;
        this.user = user;
        this.status = status;
    }
}
