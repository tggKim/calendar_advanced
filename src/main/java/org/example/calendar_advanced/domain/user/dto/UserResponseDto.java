package org.example.calendar_advanced.domain.user.dto;

import lombok.Getter;
import org.example.calendar_advanced.domain.user.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.updatedDate = user.getUpdatedDate();
    }
}
