package org.example.calendar_advanced.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.calendar_advanced.domain.comment.entity.Comment;

@Getter
public class CommentCreateRequestDto {
    @NotBlank(message = "댓글 내용은 필수 입력 값 입니다.")
    private String content;

    @NotNull(message = "scheduleId는 필수 입력 값 입니다.")
    private Long scheduleId;

    public Comment toComment(){
        return new Comment(this.content);
    }
}
