package org.example.calendar_advanced.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.dto.CommentSaveRequestDto;
import org.example.calendar_advanced.domain.comment.dto.CommentUpdateRequestDto;
import org.example.calendar_advanced.domain.comment.service.CommentService;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleUpdateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto, HttpServletRequest httpServletRequest){
        Long sessionUserId = getUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(commentService.saveComment(commentSaveRequestDto, sessionUserId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable("commentId") Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("commentId") Long commentId, @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = getUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(commentService.updateComment(commentId, sessionUserId, commentUpdateRequestDto), HttpStatus.OK);
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = null;
        if(session != null && session.getAttribute("userId") != null){
            userId = (Long) session.getAttribute("userId");
        }
        return userId;
    }

}
