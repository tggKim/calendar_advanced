package org.example.calendar_advanced.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

}
