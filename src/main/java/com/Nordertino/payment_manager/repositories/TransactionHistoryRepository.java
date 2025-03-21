package com.Nordertino.payment_manager.repositories;

import com.Nordertino.payment_manager.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
}
