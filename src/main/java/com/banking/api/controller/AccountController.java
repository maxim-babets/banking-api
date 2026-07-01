package com.banking.api.controller;

import com.banking.api.dto.account.AccountRequestDTO;
import com.banking.api.dto.account.AccountResponseDTO;
import com.banking.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO request){
        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @GetMapping("/user/{id}")
    public List<AccountResponseDTO> getAccountByUserId(@PathVariable Long id){
        return accountService.getAccountByUserId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
    }
}
