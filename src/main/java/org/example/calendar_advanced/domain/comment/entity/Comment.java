package org.example.calendar_advanced.domain.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
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
