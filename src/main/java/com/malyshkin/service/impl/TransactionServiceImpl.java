package com.malyshkin.service.impl;

import com.malyshkin.entity.Transaction;
import com.malyshkin.repository.TransactionRepository;
import com.malyshkin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void save(Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public List<Transaction> findAll(long categoryId) {
        return transactionRepository.findAll();
    }
}
