package org.example.calendar_advanced.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleSaveRequestDto;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.domain.user.service.UserService;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto saveSchedule(ScheduleSaveRequestDto scheduleSaveRequestDto, Long userId){
        Schedule schedule = scheduleSaveRequestDto.toSchedule();

        User findUser = userRepository.findById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));

        schedule.setUser(findUser);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.builder()
                .scheduleId(saveSchedule.getScheduleId())
                .userId(findUser.getUserId())
                .username(findUser.getUsername())
                .title(saveSchedule.getTitle())
                .todo(saveSchedule.getTodo())
                .createdTime(saveSchedule.getCreatedDate())
                .updatedTime(saveSchedule.getUpdatedDate())
                .build();
    }

    @Transactional
    public List<ScheduleResponseDto> getAllSchedules(){
        return scheduleRepository.getAllSchedules();
    }
}
