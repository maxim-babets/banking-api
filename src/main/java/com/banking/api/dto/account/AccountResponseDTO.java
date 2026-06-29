package com.banking.api.dto.account;

import com.banking.api.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private Long accountNumber;
    private String institutionNumber;
    private String transitNumber;
    private BigDecimal balance;
    private AccountType accountType;
}
