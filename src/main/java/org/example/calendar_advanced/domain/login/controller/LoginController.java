package org.example.calendar_advanced.domain.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.example.calendar_advanced.domain.login.dto.LoginRequestDto;
import org.example.calendar_advanced.domain.login.service.LoginService;
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
    public ResponseEntity login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest){

//        HttpSession session = httpServletRequest.getSession();
//        if(session != null && session.getAttribute("userId") != null){
//            Map<String, String> message = new HashMap<>();
//            message.put("message", "이미 로그인 상태입니다.");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
//        }


        return null;
    }

    @PostMapping("/api/logout")
    public ResponseEntity logout(){
        return null;
    }
}
