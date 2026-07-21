package com.banking.api.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BankingApiException {
    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
