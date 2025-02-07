package org.example.calendar_advanced.domain.login.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.login.dto.LoginRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto validateLoginAndReturnUserDto(LoginRequestDto loginRequestDto){
        User findUser = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND_BY_EMAIL));

        if(!findUser.getPassword().equals(loginRequestDto.getPassword())){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }

        return new UserResponseDto(findUser);
    }

}
