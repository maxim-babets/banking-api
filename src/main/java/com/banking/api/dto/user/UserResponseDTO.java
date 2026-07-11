package com.banking.api.dto.user;

import com.banking.api.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleType role;
}
