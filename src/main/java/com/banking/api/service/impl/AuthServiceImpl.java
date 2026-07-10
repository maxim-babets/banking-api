package com.banking.api.service.impl;

import com.banking.api.dto.auth.LoginResponse;
import com.banking.api.dto.auth.LoginRequestDTO;
import com.banking.api.model.User;
import com.banking.api.repository.UserRepository;
import com.banking.api.security.JwtService;
import com.banking.api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));
        String rawPassword = request.getPassword();
        String encodedPassword = user.getPassword();
        boolean isMatch = passwordEncoder.matches(rawPassword,encodedPassword);
        if(!isMatch) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password");
        }
        return new LoginResponse(user.getId(),user.getEmail(), "Login successful", jwtService.generateToken(user.getEmail()));
        }
    }
