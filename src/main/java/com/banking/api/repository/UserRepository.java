package com.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
