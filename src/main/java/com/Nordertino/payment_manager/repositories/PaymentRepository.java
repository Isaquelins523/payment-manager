package com.Nordertino.payment_manager.repositories;

import com.Nordertino.payment_manager.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
