package com.banking.api.exception;

import org.springframework.http.HttpStatus;

public class ResourceAccessDeniedException extends BankingApiException {
    public ResourceAccessDeniedException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
