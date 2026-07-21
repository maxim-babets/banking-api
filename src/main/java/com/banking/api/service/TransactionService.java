package com.banking.api.service;

import com.banking.api.dto.transaction.TransactionRequestDTO;
import com.banking.api.dto.transaction.TransactionResponseDTO;

import java.util.List;



public interface TransactionService {

    TransactionResponseDTO createTransaction(TransactionRequestDTO request);

    TransactionResponseDTO getTransactionById(Long id,Long currentUserId);

    List<TransactionResponseDTO> getTransactionByAccountId(Long id, Long currentUserId);
}
