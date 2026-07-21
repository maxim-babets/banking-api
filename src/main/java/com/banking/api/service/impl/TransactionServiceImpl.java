package com.banking.api.service.impl;

import com.banking.api.dto.transaction.TransactionRequestDTO;
import com.banking.api.dto.transaction.TransactionResponseDTO;
import com.banking.api.exception.InsufficientFundsException;
import com.banking.api.exception.ResourceAccessDeniedException;
import com.banking.api.exception.ResourceNotFoundException;
import com.banking.api.model.Account;
import com.banking.api.model.Transaction;
import com.banking.api.repository.AccountRepository;
import com.banking.api.repository.TransactionRepository;
import com.banking.api.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
        Transaction transaction = new Transaction();
        Account accountSender = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(()->new ResourceNotFoundException("Account not found"));

        Account accountReceiver = accountRepository.findById(request.getToAccountId())
                .orElseThrow(()->new ResourceNotFoundException("Account not found"));

        if(accountSender.getBalance().compareTo(request.getAmount())<0){
            throw new InsufficientFundsException("Insufficient funds");
        }

        accountSender.setBalance(accountSender.getBalance().subtract(request.getAmount()));
        accountReceiver.setBalance(accountReceiver.getBalance().add(request.getAmount()));

        Account savedAccountSender = accountRepository.save(accountSender);
        Account savedAccountReceiver = accountRepository.save(accountReceiver);

        transaction.setFromAccount(savedAccountSender);
        transaction.setToAccount(savedAccountReceiver);
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setDateTime(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransactionResponseDTO(savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getTransactionType(),
                savedTransaction.getFromAccount().getId(),
                savedTransaction.getToAccount().getId(),
                savedTransaction.getDateTime());
    }

    @Override
    public TransactionResponseDTO getTransactionById(Long id,Long currentUserId) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Transaction not found with id: " + id));
        if(!(transaction.getFromAccount().getUser().getId().equals(currentUserId) || transaction.getToAccount().getUser().getId().equals(currentUserId))){
            throw  new ResourceAccessDeniedException("Access Denied");
        }
        return new TransactionResponseDTO(transaction.getId(),transaction.getAmount(), transaction.getTransactionType(),transaction.getFromAccount().getId(),transaction.getToAccount().getId(),transaction.getDateTime());
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByAccountId(Long id,Long currentUserId) {
        Account accountId = accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account not found"));
        if(!accountId.getUser().getId().equals(currentUserId)){
            throw  new ResourceAccessDeniedException("Access Denied");
        }
       List<Transaction> transactions = transactionRepository.findByFromAccountIdOrToAccountId(id,id);

       return transactions.stream()
               .map(transaction -> new TransactionResponseDTO(transaction.getId(),transaction.getAmount(),transaction.getTransactionType(),transaction.getFromAccount().getId(),transaction.getToAccount().getId(),transaction.getDateTime()))
               .toList();

    }
}
