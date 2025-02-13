package org.example.calendar_advanced.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendar_advanced.domain.baseentity.BaseEntity;
import org.example.calendar_advanced.domain.comment.entity.Comment;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Builder
    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void updateUsername(String username){
        this.username = username;
    }
}
