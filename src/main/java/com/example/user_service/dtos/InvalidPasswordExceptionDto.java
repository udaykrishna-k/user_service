package com.example.user_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPasswordExceptionDto {
    private String message;
}
