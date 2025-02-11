package org.example.calendar_advanced.domain.schedule.repository;

import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT u.userId FROM Schedule s JOIN s.user u WHERE s.scheduleId = :scheduleId")
    Optional<String> getUserIdByScheduleId(@Param("scheduleId") Long scheduleId);

    @Query("SELECT new org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto(s.scheduleId, u.userId, u.username, s.title, s.todo, COUNT(c.commentId), s.createdDate, s.updatedDate)" +
            " FROM Schedule s" +
            " INNER JOIN s.user u" +
            " LEFT JOIN s.comments c" +
            " GROUP BY s.scheduleId, u.userId, u.username, s.title, s.todo, s.createdDate, s.updatedDate")
    List<ScheduleResponseDto> getAllSchedules();

    @Query("SELECT new org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto(s.scheduleId, u.userId, u.username, s.title, s.todo, COUNT(c.commentId), s.createdDate, s.updatedDate)" +
            " FROM Schedule s" +
            " INNER JOIN s.user u" +
            " LEFT JOIN s.comments c" +
            " WHERE s.scheduleId = :scheduleId" +
            " GROUP BY s.scheduleId, u.userId, u.username, s.title, s.todo, s.createdDate, s.updatedDate")
    Optional<ScheduleResponseDto> getScheduleById(@Param("scheduleId") Long scheduleId);
}
