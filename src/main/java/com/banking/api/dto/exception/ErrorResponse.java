package com.banking.api.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private  LocalDateTime timestamp;
    private String error;
    private String message;

    public ErrorResponse(String error,String message){
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.message = message;
    }
}
