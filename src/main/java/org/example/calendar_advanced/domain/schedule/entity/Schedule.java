package org.example.calendar_advanced.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendar_advanced.domain.comment.entity.Comment;
import org.example.calendar_advanced.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
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

    public Schedule(String title, String todo){
        this.title = title;
        this.todo = todo;
    }

    public void setUser(User user){
        this.user = user;
        user.addSchedule(this);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

}