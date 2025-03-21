package com.Nordertino.payment_manager.controller;

import com.Nordertino.payment_manager.domain.TransactionHistory;
import com.Nordertino.payment_manager.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class WebhookController {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @PostMapping("/webhook")
    public ResponseEntity<?> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            // Pega o ID da transação e o status do payload recebido
            String transactionId = (String) payload.get("id");
            String status = (String) payload.get("status");

            // Busca a transação pelo ID
            Optional<TransactionHistory> optionalTransaction = transactionHistoryRepository.findById(Long.valueOf(transactionId));

            if (optionalTransaction.isPresent()) {
                TransactionHistory transaction = optionalTransaction.get();
                transaction.setStatus(status);
                transactionHistoryRepository.save(transaction);

                return ResponseEntity.ok("Status da transação atualizado para: " + status);
            } else {
                return ResponseEntity.status(404).body("Transação não encontrada");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o webhook: " + e.getMessage());
        }
    }
}
