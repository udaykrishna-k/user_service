package com.example.user_service.controllers;

import com.example.user_service.dtos.SignUpRequestDto;
import com.example.user_service.dtos.SignUpResponseDto;
import com.example.user_service.models.User;
import com.example.user_service.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        User user = userService.signUp(signUpRequestDto.getUsername(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return SignUpResponseDto.fromUser(user);
    }
}
