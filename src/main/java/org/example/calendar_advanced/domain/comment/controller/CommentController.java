package org.example.calendar_advanced.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.dto.CommentDeleteRequestDto;
import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.dto.CommentCreateRequestDto;
import org.example.calendar_advanced.domain.comment.dto.CommentUpdateRequestDto;
import org.example.calendar_advanced.domain.comment.service.CommentService;
import org.example.calendar_advanced.global.util.SessionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final SessionUtil sessionUtil;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentCreateRequestDto commentCreateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(commentService.createComment(commentCreateRequestDto, sessionUserId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComments(){
        return new ResponseEntity<>(commentService.findAllComments(), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable("commentId") Long commentId){
        return new ResponseEntity<>(commentService.findCommentById(commentId), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("commentId") Long commentId, @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(commentService.updateComment(commentId, sessionUserId, commentUpdateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable("commentId") Long commentId, @Valid @RequestBody CommentDeleteRequestDto commentDeleteRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        commentService.deleteComment(commentId, sessionUserId, commentDeleteRequestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
