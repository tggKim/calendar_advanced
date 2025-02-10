package org.example.calendar_advanced.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.dto.CommentSaveRequestDto;
import org.example.calendar_advanced.domain.comment.entity.Comment;
import org.example.calendar_advanced.domain.comment.repository.CommentRepository;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto saveComment(CommentSaveRequestDto commentSaveRequestDto, Long userId){
        Comment comment = commentSaveRequestDto.toComment();

        Schedule findSchedule = scheduleRepository.findById(commentSaveRequestDto.getScheduleId()).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
        comment.setSchedule(findSchedule);

        User findUser = userRepository.findById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
        comment.setUser(findUser);

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.builder()
                .commentId(savedComment.getCommentId())
                .scheduleId(findSchedule.getScheduleId())
                .userId(findUser.getUserId())
                .username(findUser.getUsername())
                .content(savedComment.getContent())
                .createdTime(savedComment.getCreatedDate())
                .updatedTime(savedComment.getUpdatedDate())
                .build();
    }

    public List<CommentResponseDto> getAllComments(){
        return commentRepository.getAllComments();
    }

    public CommentResponseDto getCommentById(Long commentId){
        return commentRepository.getCommentById(commentId).orElseThrow(() -> new Exception404(ErrorCode.COMMENT_NOT_FOUND));
    }
}
