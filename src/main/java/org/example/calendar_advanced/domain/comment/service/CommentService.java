package org.example.calendar_advanced.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
}
