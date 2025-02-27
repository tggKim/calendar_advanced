package org.example.calendar_advanced.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.dto.CommentDeleteRequestDto;
import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.dto.CommentCreateRequestDto;
import org.example.calendar_advanced.domain.comment.dto.CommentUpdateRequestDto;
import org.example.calendar_advanced.domain.comment.entity.Comment;
import org.example.calendar_advanced.domain.comment.repository.CommentRepository;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.config.PasswordEncoder;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception403;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CommentResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto, Long userId){
        Comment comment = commentCreateRequestDto.toComment();

        Schedule findSchedule = scheduleRepository.findById(commentCreateRequestDto.getScheduleId()).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
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

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllComments(){
        return commentRepository.findAllComments();
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findCommentById(Long commentId){
        return commentRepository.findCommentById(commentId).orElseThrow(() -> new Exception404(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, Long sessionUserId, CommentUpdateRequestDto commentUpdateRequestDto){

        validateLoginUser(commentId,  sessionUserId);

        validatePassword(sessionUserId, commentUpdateRequestDto.getPassword());

        // 댓글을 업데이트
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new Exception404(ErrorCode.COMMENT_NOT_FOUND));
        findComment.updateContent(commentUpdateRequestDto.getContent());

        // 댓글을 찾아서 리턴
        return commentRepository.findCommentById(commentId).orElseThrow(() -> new Exception404(ErrorCode.COMMENT_NOT_FOUND));

    }

    @Transactional
    public void deleteComment(Long commentId, Long sessionUserId, CommentDeleteRequestDto commentDeleteRequestDto){

        validateLoginUser(commentId,  sessionUserId);

        validatePassword(sessionUserId, commentDeleteRequestDto.getPassword());

        commentRepository.deleteById(commentId);

    }

    private void validateLoginUser(Long commentId, Long userId){
        // 현재 로그인한 유저의 일정인지 확인
        String findUserId = commentRepository.findUserIdByCommentId(commentId).orElseThrow(() -> new Exception404(ErrorCode.COMMENT_NOT_FOUND));
        if(userId != Long.parseLong(findUserId)){
            throw new Exception403(ErrorCode.COMMENT_ACCESS_DENIED);
        }
    }

    private void validatePassword(Long userId, String rawPassword){
        // 유저의 비밀번홀 검사
        String findPassword = userRepository.findPasswordByUserId(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
        if(!passwordEncoder.matches(rawPassword, findPassword)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }
    }
}
