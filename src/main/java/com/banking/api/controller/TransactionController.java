package com.banking.api.controller;

import com.banking.api.dto.transaction.TransactionRequestDTO;
import com.banking.api.dto.transaction.TransactionResponseDTO;
import com.banking.api.model.User;
import com.banking.api.security.CustomUserDetails;
import com.banking.api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
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
    public TransactionResponseDTO getTransactionById(@PathVariable Long id, Authentication authentication){
       User currentUser = ((CustomUserDetails)authentication.getPrincipal()).getUser();
        return transactionService.getTransactionById(id, currentUser.getId());
   }

   @GetMapping("/account/{id}")
    public List<TransactionResponseDTO> getTransactionByAccountId(@PathVariable Long id,Authentication authentication){
       User currentUser = ((CustomUserDetails)authentication.getPrincipal()).getUser();
        return transactionService.getTransactionByAccountId(id,currentUser.getId());
   }

}
