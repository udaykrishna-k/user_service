package com.example.user_service.exception_handlers;

import com.example.user_service.dtos.InvalidPasswordExceptionDto;
import com.example.user_service.dtos.UserNotFoundExceptionDto;
import com.example.user_service.exceptions.InvalidPasswordException;
import com.example.user_service.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionDto> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        UserNotFoundExceptionDto dto = new UserNotFoundExceptionDto();
        dto.setMessage(userNotFoundException.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<InvalidPasswordExceptionDto> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException){
        InvalidPasswordExceptionDto dto = new InvalidPasswordExceptionDto();
        dto.setMessage(invalidPasswordException.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
