package com.malyshkin.service;

import com.malyshkin.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public void save(Transaction transaction);
    public List<Transaction> findAll(long categoryId);
}
