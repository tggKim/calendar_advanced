package org.example.calendar_advanced.domain.schedule.repository;

import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT new org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto(s.scheduleId, u.userId, u.username, s.title, s.todo, s.createdDate, s.updatedDate)  FROM Schedule s JOIN s.user u")
    List<ScheduleResponseDto> getAllSchedules();
}
