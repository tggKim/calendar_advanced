package org.example.calendar_advanced.user.dto;

import lombok.Getter;
import org.example.calendar_advanced.user.entity.User;

@Getter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
