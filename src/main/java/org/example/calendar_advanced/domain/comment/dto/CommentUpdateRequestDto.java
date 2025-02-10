package org.example.calendar_advanced.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    @NotBlank(message = "댓글 내용은 필수 입력 값 입니다.")
    private String content;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다,")
    private String password;
}
