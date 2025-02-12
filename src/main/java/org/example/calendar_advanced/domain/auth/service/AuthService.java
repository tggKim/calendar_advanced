package org.example.calendar_advanced.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.auth.dto.AuthRequestDto;
import org.example.calendar_advanced.domain.auth.dto.AuthResponseDto;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.config.PasswordEncoder;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AuthResponseDto validateLoginAndReturnUserDto(AuthRequestDto loginRequestDto){
        User findUser = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND_BY_EMAIL));

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), findUser.getPassword())){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }

        return new AuthResponseDto(findUser);
    }

}
