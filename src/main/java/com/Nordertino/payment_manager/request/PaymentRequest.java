package com.Nordertino.payment_manager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Double amount;           // Valor do pagamento
    private String description;      // Descrição do pagamento
    private String email;            // Email do pagador
    private String status;
}
