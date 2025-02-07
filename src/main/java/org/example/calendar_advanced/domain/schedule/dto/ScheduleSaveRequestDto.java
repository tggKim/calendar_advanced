package org.example.calendar_advanced.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSaveRequestDto {

    @NotBlank(message = "제목은 필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "할 일은 필수 입력 값 입니다.")
    private String todo;

    public Schedule toSchedule(){
        return new Schedule(this.title, this.todo);
    }

}
