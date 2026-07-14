package com.banking.api.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BankingApiException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
