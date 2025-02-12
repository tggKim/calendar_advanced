package org.example.calendar_advanced.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.auth.dto.AuthRequestDto;
import org.example.calendar_advanced.domain.auth.dto.AuthResponseDto;
import org.example.calendar_advanced.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto loginRequestDto, HttpServletRequest httpServletRequest){

        AuthResponseDto authResponseDto = loginService.validateLoginAndReturnUserDto(loginRequestDto);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", authResponseDto.getUserId());

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
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
