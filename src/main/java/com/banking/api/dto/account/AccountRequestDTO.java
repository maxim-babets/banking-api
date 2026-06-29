package com.banking.api.dto.account;

import com.banking.api.model.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotNull
    private AccountType accountType;
    private Long userId;
}
