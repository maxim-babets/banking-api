package com.banking.api.controller;

import com.banking.api.dto.transaction.TransactionRequestDTO;
import com.banking.api.dto.transaction.TransactionResponseDTO;
import com.banking.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

   @PostMapping
   public TransactionResponseDTO createTransaction(@Valid @RequestBody TransactionRequestDTO request){
        return transactionService.createTransaction(request);
   }

   @GetMapping("/{id}")
    public TransactionResponseDTO getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
   }

   @GetMapping("/account/{id}")
    public List<TransactionResponseDTO> getTransactionByAccountId(@PathVariable Long id){
        return transactionService.getTransactionByAccountId(id);
   }

}
