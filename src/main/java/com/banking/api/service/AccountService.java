package com.banking.api.service;

import com.banking.api.dto.account.AccountRequestDTO;
import com.banking.api.dto.account.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    AccountResponseDTO createAccount(AccountRequestDTO request);

    AccountResponseDTO getAccountById(Long id);

    List<AccountResponseDTO> getAccountByUserId(Long id);

    void deleteAccount(Long id);
}

