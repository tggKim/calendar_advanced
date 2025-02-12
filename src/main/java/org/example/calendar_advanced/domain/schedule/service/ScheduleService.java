package org.example.calendar_advanced.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleDeleteRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleSaveRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleUpdateRequestDto;
import org.example.calendar_advanced.domain.schedule.entity.Schedule;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.domain.user.service.UserService;
import org.example.calendar_advanced.global.config.PasswordEncoder;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception403;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

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
                .commentCount(0L)
                .createdTime(saveSchedule.getCreatedDate())
                .updatedTime(saveSchedule.getUpdatedDate())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getAllSchedules(Pageable pageable){
        return scheduleRepository.getAllSchedules(pageable);
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto getScheduleById(Long scheduleId){
        return scheduleRepository.getScheduleById(scheduleId).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, Long sessionUserId, ScheduleUpdateRequestDto scheduleUpdateRequestDto){

        validateLoginUser(scheduleId,  sessionUserId);

        validatePassword(sessionUserId, scheduleUpdateRequestDto.getPassword());

        // 일정을 업데이트
        Schedule findSchedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
        findSchedule.updateTitle(scheduleUpdateRequestDto.getTitle());
        findSchedule.updateTodo(scheduleUpdateRequestDto.getTodo());

        // 일정을 찾아서 리턴
        return scheduleRepository.getScheduleById(scheduleId).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));

    }

    @Transactional
    public void deleteSchedule(Long scheduleId, Long sessionUserId, ScheduleDeleteRequestDto scheduleDeleteRequestDto){

        validateLoginUser(scheduleId,  sessionUserId);

        validatePassword(sessionUserId, scheduleDeleteRequestDto.getPassword());

        scheduleRepository.deleteById(scheduleId);

    }

    private void validateLoginUser(Long scheduleId, Long userId){
        // 현재 로그인한 유저의 일정인지 확인
        String findUserId = scheduleRepository.getUserIdByScheduleId(scheduleId).orElseThrow(() -> new Exception404(ErrorCode.SCHEDULE_NOT_FOUND));
        if(userId != Long.parseLong(findUserId)){
            throw new Exception403(ErrorCode.SCHEDULE_ACCESS_DENIED);
        }
    }

    private void validatePassword(Long userId, String rawPassword){
        // 유저의 비밀번홀 검사
        String findPassword = userRepository.findPasswordByUserId(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
        if(!passwordEncoder.matches(rawPassword, findPassword)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }
    }
}
