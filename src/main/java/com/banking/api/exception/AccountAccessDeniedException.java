package com.banking.api.exception;

import org.springframework.http.HttpStatus;

public class AccountAccessDeniedException extends BankingApiException {
    public AccountAccessDeniedException(String message ) {
        super(message,HttpStatus.NOT_FOUND);
    }

}
