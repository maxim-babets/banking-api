package com.banking.api.service;

import com.banking.api.dto.auth.LoginResponse;
import com.banking.api.dto.auth.LoginRequestDTO;
import com.banking.api.dto.user.UserRequestDTO;
import com.banking.api.dto.user.UserResponseDTO;

public interface AuthService {

    LoginResponse login(LoginRequestDTO request);

    UserResponseDTO register(UserRequestDTO request);
}
