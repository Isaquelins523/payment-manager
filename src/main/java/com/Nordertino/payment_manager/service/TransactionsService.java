package com.Nordertino.payment_manager.service;

import com.Nordertino.payment_manager.domain.TransactionHistory;
import com.Nordertino.payment_manager.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public void save(TransactionHistory transactionHistory) {
        transactionHistoryRepository.save(transactionHistory);
    }
}
