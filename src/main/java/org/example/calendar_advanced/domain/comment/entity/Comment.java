package org.example.calendar_advanced.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendar_advanced.domain.baseentity.BaseEntity;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {
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

    public Comment(String content){
        this.content = content;
    }

    public void updateContent(String content){
        this.content = content;
    }

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
        schedule.addComment(this);
    }

    public void setUser(User user){
        this.user = user;
        user.addComment(this);
    }
}
