package org.example.calendar_advanced.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleUpdateRequestDto {
    @NotBlank(message = "제목은 필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "할 일은 필수 입력 값 입니다.")
    private String todo;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다,")
    private String password;
}
