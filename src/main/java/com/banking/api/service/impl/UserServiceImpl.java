package com.banking.api.service.impl;

import com.banking.api.dto.user.UserRequestDTO;
import com.banking.api.dto.user.UserResponseDTO;
import com.banking.api.model.RoleType;
import com.banking.api.model.User;
import com.banking.api.repository.AccountRepository;
import com.banking.api.repository.UserRepository;
import com.banking.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(RoleType.USER);

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user  = userRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, " User not found with id: " +  id));
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getRole());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getRole()))
                .toList();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User not found with id: " + id));
    accountRepository.deleteByUserId(id);
    userRepository.delete(user);
    }
}
