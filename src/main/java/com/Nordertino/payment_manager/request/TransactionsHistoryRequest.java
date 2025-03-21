package com.Nordertino.payment_manager.request;

import com.Nordertino.payment_manager.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionsHistoryRequest(Long id, BigDecimal amount, String payment_method, String status, String description, LocalDateTime transaction_date, User user){
}
