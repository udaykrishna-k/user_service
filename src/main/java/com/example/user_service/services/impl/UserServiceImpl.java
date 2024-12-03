package com.example.user_service.services.impl;

import com.example.user_service.exceptions.InvalidPasswordException;
import com.example.user_service.exceptions.UserNotFoundException;
import com.example.user_service.models.Token;
import com.example.user_service.models.User;
import com.example.user_service.repos.TokenRepo;
import com.example.user_service.repos.UserRepo;
import com.example.user_service.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private TokenRepo tokenRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, TokenRepo tokenRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setHashedPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Unknown user");
        }
        User user = optionalUser.get();
        if (! passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        Token token = generateToken(user);
        return tokenRepo.save(token);
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepo.findByValueAndExpiryAtGreaterThan(token, System.currentTimeMillis());
        if (optionalToken.isEmpty()) {
            return null;
        }
        return optionalToken.get().getUser();
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setExpiryAt(System.currentTimeMillis() + (60*60*1000));
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        return token;
    }
}
