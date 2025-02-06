package org.example.calendar_advanced.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.example.calendar_advanced.domain.user.entity.User;

@Getter
public class UserSaveRequestDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다,")
    private String password;

    public User toUser(){
        return new User(this.username, this.email, this.password);
    }
}
