package org.example.calendar_advanced.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.dto.CommentSaveRequestDto;
import org.example.calendar_advanced.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentResponseDto saveComment(CommentSaveRequestDto commentSaveRequestDto, Long userId){

    }
}
