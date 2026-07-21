package com.banking.api.exception;


public class InsufficientFundsException extends BankingApiException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
