package com.banking.api.exception;

import org.springframework.http.HttpStatus;


public class BankingApiException extends RuntimeException {
    private HttpStatus httpStatus;

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BankingApiException(String message) {
        super(message);
        this.httpStatus = DEFAULT_STATUS;

    }

    public BankingApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
