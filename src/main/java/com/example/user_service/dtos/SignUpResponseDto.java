package com.example.user_service.dtos;

import com.example.user_service.models.Role;
import com.example.user_service.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpResponseDto {
    private String username;
    private String email;
    private List<Role> roles;

    public static SignUpResponseDto fromUser(User user) {
        SignUpResponseDto dto = new SignUpResponseDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
}
