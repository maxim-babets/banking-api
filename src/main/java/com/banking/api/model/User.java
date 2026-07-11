package com.banking.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "user")
    List<Account> accounts;

    String firstName;
    String lastName;
    String email;
    String password;

    @Enumerated(EnumType.STRING)
    RoleType role;


}
