package org.example.calendar_advanced.domain.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleDeleteRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleSaveRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleUpdateRequestDto;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody ScheduleSaveRequestDto scheduleSaveRequestDto, HttpServletRequest httpServletRequest){

        Long userId = getUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleSaveRequestDto, userId), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
        return new ResponseEntity<>(scheduleService.getAllSchedules(), HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable("scheduleId") Long scheduleId){
        return new ResponseEntity<>(scheduleService.getScheduleById(scheduleId), HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable("scheduleId") Long scheduleId, @Valid @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = getUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId, sessionUserId, scheduleUpdateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("scheduleId") Long scheduleId, @Valid @RequestBody ScheduleDeleteRequestDto scheduleDeleteRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = getUserIdBySession(httpServletRequest);

        scheduleService.deleteSchedule(scheduleId, sessionUserId, scheduleDeleteRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = null;
        if(session != null && session.getAttribute("userId") != null){
            userId = (Long) session.getAttribute("userId");
        }
        return userId;
    }
}
