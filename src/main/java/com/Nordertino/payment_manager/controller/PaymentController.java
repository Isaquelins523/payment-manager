package com.Nordertino.payment_manager.controller;

import com.Nordertino.payment_manager.domain.TransactionHistory;
import com.Nordertino.payment_manager.domain.User;
import com.Nordertino.payment_manager.repositories.UserRepository;
import com.Nordertino.payment_manager.request.PaymentRequest;
import com.Nordertino.payment_manager.service.TransactionsService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionsService transactionsService;


    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest request) {
        try {

            // Validações básicas
            if (request.getAmount() == null || request.getAmount() <= 0 || request.getEmail().isBlank()) {
                return ResponseEntity.badRequest().body("Dados de pagamento inválidos");
            }

            // Configura o token do Mercado Pago
            MercadoPagoConfig.setAccessToken("TEST-8237077071375977-032008-518b623c78d51fa6294cabf9b7acf5c2-374825125");

            PaymentClient client = new PaymentClient();

            // Criação da requisição de pagamento
            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    .transactionAmount(new BigDecimal(request.getAmount()))
                    .description(request.getDescription())
                    .paymentMethodId("pix")
                    .payer(PaymentPayerRequest.builder().email(request.getEmail()).build())
                    .build();

            Payment payment = client.create(createRequest);

            if (payment != null) {
                // Busca o usuário ou lança exceção
                User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                // Define o status da transação com base no retorno do Mercado Pago
                String status = payment.getStatus() != null ? payment.getStatus() : "pending";

                // Cria e salva o histórico da transação com status como String e user como User
                TransactionHistory transactionHistory = new TransactionHistory(
                        null,
                        new BigDecimal(request.getAmount()),
                        "pix",
                        request.getDescription(),
                        LocalDateTime.now(),
                        user,
                        status
                );


                transactionsService.save(transactionHistory);

                // Retorna os dados essenciais da transação
                return ResponseEntity.ok(Map.of(
                        "status", status,
                        "payer", user.getName(),
                        "amount", request.getAmount()
                ));

            } else {
                return ResponseEntity.status(400).body("Erro ao criar pagamento.");
            }

        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Erro: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro inesperado: " + ex.getMessage());
        }
    }
}
