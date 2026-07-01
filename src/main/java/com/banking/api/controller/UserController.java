package com.banking.api.controller;

import com.banking.api.dto.user.UserRequestDTO;
import com.banking.api.dto.user.UserResponseDTO;
import com.banking.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public  UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
