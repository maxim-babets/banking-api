package com.banking.api.dto.transaction;

import com.banking.api.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {
    @NotNull
    private Long fromAccountId;
    @NotNull
    private Long toAccountId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType transactionType;
}
