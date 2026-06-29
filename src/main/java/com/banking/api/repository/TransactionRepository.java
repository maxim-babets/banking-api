package com.banking.api.repository;

import com.banking.api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository <Transaction,Long> {
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromId, Long toId);
}
