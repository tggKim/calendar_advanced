package org.example.calendar_advanced.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private Long scheduleId;
    private Long userId;
    private String username;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    @Builder
    public CommentResponseDto(Long commentId, Long scheduleId, Long userId, String username, String content, LocalDateTime createdTime, LocalDateTime updatedTime){
        this.commentId = commentId;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
