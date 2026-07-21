package com.banking.api.service.impl;

import com.banking.api.dto.account.AccountRequestDTO;
import com.banking.api.dto.account.AccountResponseDTO;
import com.banking.api.exception.AccountAccessDeniedException;
import com.banking.api.exception.ResourceNotFoundException;
import com.banking.api.model.Account;
import com.banking.api.model.User;
import com.banking.api.repository.AccountRepository;
import com.banking.api.repository.UserRepository;
import com.banking.api.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    private static final String INSTITUTION_NUMBER = "001";
    private static final String TRANSIT_NUMBER = "00001";

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    private Long generateAccountNumber(){
        Random random = new Random();
        return random.nextLong(1000000000L, 9999999999L);
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request) {
        Account account = new Account();
        User user  = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "User not found"));
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setBalance(BigDecimal.ZERO);

        Account savedAccount = accountRepository.save(account);
        return new AccountResponseDTO(savedAccount.getId(),
                savedAccount.getAccountNumber(),
                INSTITUTION_NUMBER,
                TRANSIT_NUMBER,
                savedAccount.getBalance(),
                savedAccount.getAccountType());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id, Long currentUserId) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account not found with id: " + id ));
        if (!account.getUser().getId().equals(currentUserId)){
            throw  new AccountAccessDeniedException("Access Denied");
        }
        return new AccountResponseDTO(account.getId(),account.getAccountNumber(),INSTITUTION_NUMBER,TRANSIT_NUMBER,account.getBalance(),account.getAccountType());
    }

    @Override
    public List<AccountResponseDTO> getAccountByUserId(Long id) {
        List<Account> accounts = accountRepository.findByUserId(id);
        return accounts.stream()
                .map(account -> new AccountResponseDTO(account.getId(),account.getAccountNumber(),INSTITUTION_NUMBER,TRANSIT_NUMBER,account.getBalance(),account.getAccountType()))
                .toList();
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found with id: " + id));
        accountRepository.delete(account);
    }
}
