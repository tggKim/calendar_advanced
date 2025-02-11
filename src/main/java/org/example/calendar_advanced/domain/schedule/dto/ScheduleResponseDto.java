package org.example.calendar_advanced.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private Long userId;
    private String username;
    private String title;
    private String todo;
    private Long commentCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    @Builder
    public ScheduleResponseDto(Long scheduleId, Long userId, String username, String title, String todo, Long commentCount, LocalDateTime createdTime, LocalDateTime updatedTime){
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.commentCount = commentCount;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
