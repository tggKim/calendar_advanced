package org.example.calendar_advanced.comment.entity;

import jakarta.persistence.*;
import org.example.calendar_advanced.schedule.entity.Schedule;
import org.example.calendar_advanced.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
        schedule.addComment(this);
    }

    public void setUser(User user){
        this.user = user;
        user.addComment(this);
    }
}
