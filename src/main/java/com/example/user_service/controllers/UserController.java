package com.example.user_service.controllers;

import com.example.user_service.dtos.LoginRequestDto;
import com.example.user_service.dtos.SignUpRequestDto;
import com.example.user_service.dtos.SignUpResponseDto;
import com.example.user_service.exceptions.InvalidPasswordException;
import com.example.user_service.exceptions.UserNotFoundException;
import com.example.user_service.models.Token;
import com.example.user_service.models.User;
import com.example.user_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;

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

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginRequestDto loginRequetDto) throws UserNotFoundException, InvalidPasswordException {
        Token token = userService.login(loginRequetDto.getEmail(), loginRequetDto.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<User> validateToken(@PathVariable("token") String token) throws UnexpectedException {
        User user = userService.validateToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
