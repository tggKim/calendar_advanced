package org.example.calendar_advanced.domain.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleDeleteRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleCreateRequestDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleUpdateRequestDto;
import org.example.calendar_advanced.domain.schedule.service.ScheduleService;
import org.example.calendar_advanced.global.util.SessionUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final SessionUtil sessionUtil;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto, HttpServletRequest httpServletRequest){

        Long userId = sessionUtil.findUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(scheduleService.createSchedule(scheduleCreateRequestDto, userId), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(@PageableDefault(page = 0, size = 10, sort = "scheduleId", direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(scheduleService.findAllSchedules(pageable), HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable("scheduleId") Long scheduleId){
        return new ResponseEntity<>(scheduleService.findScheduleById(scheduleId), HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable("scheduleId") Long scheduleId, @Valid @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId, sessionUserId, scheduleUpdateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("scheduleId") Long scheduleId, @Valid @RequestBody ScheduleDeleteRequestDto scheduleDeleteRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        scheduleService.deleteSchedule(scheduleId, sessionUserId, scheduleDeleteRequestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
