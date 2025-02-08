package org.example.calendar_advanced.domain.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.example.calendar_advanced.domain.login.dto.LoginRequestDto;
import org.example.calendar_advanced.domain.login.service.LoginService;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest){

        UserResponseDto userResponseDto = loginService.validateLoginAndReturnUserDto(loginRequestDto);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", userResponseDto.getUserId());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        if(session != null){
            session.removeAttribute("userId");
        }

        Map<String, String> message = new HashMap<>();
        message.put("message", "로그아웃 되었습니다.");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
