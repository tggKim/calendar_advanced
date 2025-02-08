package org.example.calendar_advanced.domain.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleResponseDto;
import org.example.calendar_advanced.domain.schedule.dto.ScheduleSaveRequestDto;
import org.example.calendar_advanced.domain.schedule.repository.ScheduleRepository;
import org.example.calendar_advanced.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody ScheduleSaveRequestDto scheduleSaveRequestDto, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();
        Long userId = null;
        if(session != null && session.getAttribute("userId") != null){
            userId = (Long) session.getAttribute("userId");
        }
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleSaveRequestDto, userId), HttpStatus.CREATED);

    }
}
