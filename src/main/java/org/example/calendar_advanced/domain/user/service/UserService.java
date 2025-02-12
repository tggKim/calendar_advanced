package org.example.calendar_advanced.domain.user.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.dto.UserCreateRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.config.PasswordEncoder;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception403;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.example.calendar_advanced.global.error.exception.Exception409;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;

    // 유저 저장
    @Transactional
    public UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto){

        if(userRepository.existsByEmail(userCreateRequestDto.getEmail())){
            throw new Exception409(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(userCreateRequestDto.getPassword());

        User savedUser = userRepository.save(new User(userCreateRequestDto.getUsername(), userCreateRequestDto.getEmail(), encodedPassword));
        return new UserResponseDto(savedUser);
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(findUser);
    }

    // 유저 모두 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUsers(){
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    // 유저 업데이트
    @Transactional
    public UserResponseDto updateUser(Long requestUserId, Long sessionUserId, UserUpdateRequestDto userUpdateRequestDto){
        User findUser = userRepository.findById(requestUserId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));

        validateLoginUser(requestUserId, sessionUserId);

        String rawPassword = userUpdateRequestDto.getPassword();
        String encodedPassword = findUser.getPassword();
        validatePassword(rawPassword, encodedPassword);

        findUser.updateUsername(userUpdateRequestDto.getUsername());

        entityManager.flush();

        return new UserResponseDto(findUser);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long requestUserId, Long sessionUserId, UserDeleteRequestDto userDeleteRequestDto){
        String encodedPassword = userRepository.findPasswordByUserId(requestUserId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));

        validateLoginUser(requestUserId, sessionUserId);

        validatePassword(userDeleteRequestDto.getPassword(), encodedPassword);

        userRepository.deleteById(requestUserId);
    }

    private void validateLoginUser(Long requestUserId, Long sessionUserId){
        // 현재 로그인한 유저인지 확인
        if(requestUserId != sessionUserId){
            throw new Exception403(ErrorCode.USER_ACCESS_DENIED);
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword){
        // 유저의 비밀번호 검사
        if(!passwordEncoder.matches(rawPassword, encodedPassword)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }
    }

}
