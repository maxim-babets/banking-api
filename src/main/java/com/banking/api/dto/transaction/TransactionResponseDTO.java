package com.banking.api.dto.transaction;

import com.banking.api.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private Long fromAccountId;
    private Long toAccountId;
    private LocalDateTime dateTime;
}
