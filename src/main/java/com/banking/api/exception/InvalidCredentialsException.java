package com.banking.api.exception;

public class InvalidCredentialsException extends BankingApiException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
