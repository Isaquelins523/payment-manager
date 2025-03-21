package com.Nordertino.payment_manager.controller;

import com.Nordertino.payment_manager.domain.TransactionHistory;
import com.Nordertino.payment_manager.domain.User;
import com.Nordertino.payment_manager.repositories.TransactionHistoryRepository;
import com.Nordertino.payment_manager.repositories.UserRepository;
import com.Nordertino.payment_manager.request.TransactionsHistoryRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryRepository transactionsRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestParam Long userId, @RequestBody TransactionsHistoryRequest data) {
        try {
            // Validação do usuário
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("message", "Usuário não encontrado."));
            }

            // Criação do histórico de transação
            User user = optionalUser.get();
            TransactionHistory newTransactionHistory = new TransactionHistory(data);
            newTransactionHistory.setUser(user);
            newTransactionHistory.setTransaction_date(LocalDateTime.now());

            // Definir o status como "pending" ou outro valor padrão, se não fornecido
            if (newTransactionHistory.getStatus() == null || newTransactionHistory.getStatus().isEmpty()) {
                newTransactionHistory.setStatus("pending");
            }

            // Salvando a transação
            TransactionHistory savedTransaction = transactionsRepository.save(newTransactionHistory);

            return ResponseEntity.ok(savedTransaction);
        } catch (Exception e) {
            // Tratamento de erro
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao salvar histórico da transação", "error", e.getMessage()));
        }
    }
}
