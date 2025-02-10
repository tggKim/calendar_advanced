package org.example.calendar_advanced.domain.comment.repository;

import org.example.calendar_advanced.domain.comment.dto.CommentResponseDto;
import org.example.calendar_advanced.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new org.example.calendar_advanced.domain.comment.dto.CommentResponseDto(c.commentId, s.scheduleId, u.userId, u.username, c.content, c.createdDate, c.updatedDate)" +
            " FROM Comment c JOIN c.user u JOIN c.schedule s")
    List<CommentResponseDto> getAllComments();
}
