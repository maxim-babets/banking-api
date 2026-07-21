package com.banking.api.service.impl;

import com.banking.api.dto.auth.LoginResponse;
import com.banking.api.dto.auth.LoginRequestDTO;
import com.banking.api.dto.user.UserRequestDTO;
import com.banking.api.dto.user.UserResponseDTO;
import com.banking.api.exception.InvalidCredentialsException;
import com.banking.api.model.User;
import com.banking.api.repository.UserRepository;
import com.banking.api.security.JwtService;
import com.banking.api.service.AuthService;
import com.banking.api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public LoginResponse login(LoginRequestDTO request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));
        String rawPassword = request.getPassword();
        String encodedPassword = user.getPassword();
        boolean isMatch = passwordEncoder.matches(rawPassword,encodedPassword);
        if(!isMatch) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return new LoginResponse(user.getId(),user.getEmail(), "Login successful", jwtService.generateToken(user.getEmail()));
        }

    public UserResponseDTO register(UserRequestDTO request){
    return userService.createUser(request);
    }
    }
