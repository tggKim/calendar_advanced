package org.example.calendar_advanced.schedule.entity;

import jakarta.persistence.*;
import org.example.calendar_advanced.comment.entity.Comment;
import org.example.calendar_advanced.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String todo;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    public void setUser(User user){
        this.user = user;
        user.addSchedule(this);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
