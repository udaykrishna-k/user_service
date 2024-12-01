package com.example.user_service.services;

import com.example.user_service.models.User;

public interface UserService {
    User signUp(String username, String email, String password);
}
