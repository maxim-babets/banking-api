package com.banking.api.repository;

import com.banking.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account,Long> {
    List<Account> findByUserId(Long userId);
}
