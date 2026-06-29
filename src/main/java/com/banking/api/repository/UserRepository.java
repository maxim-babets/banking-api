package com.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.api.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
