package com.banking.api.service;

import com.banking.api.dto.auth.LoginResponse;
import com.banking.api.dto.auth.LoginRequestDTO;

public interface AuthService {

    LoginResponse login(LoginRequestDTO request);
}
