package com.example.user_service.services;

import com.example.user_service.exceptions.InvalidPasswordException;
import com.example.user_service.exceptions.UserNotFoundException;
import com.example.user_service.models.Token;
import com.example.user_service.models.User;

public interface UserService {
    User signUp(String username, String email, String password);

    Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException;
}
