package com.banking.api.exception;

import com.banking.api.dto.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankingApiException.class)
    public ResponseEntity<ErrorResponse> handleBankingApiException(BankingApiException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ErrorResponse(exception.getHttpStatus().getReasonPhrase(),exception.getMessage()));
    }
}
