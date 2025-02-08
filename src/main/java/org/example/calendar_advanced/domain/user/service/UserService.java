package org.example.calendar_advanced.domain.user.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.dto.UserSaveRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.domain.user.entity.User;
import org.example.calendar_advanced.domain.user.repository.UserRepository;
import org.example.calendar_advanced.global.config.PasswordEncoder;
import org.example.calendar_advanced.global.error.ErrorCode;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.example.calendar_advanced.global.error.exception.Exception409;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;

    // 유저 저장
    @Transactional
    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto){

        if(userRepository.existsByEmail(userSaveRequestDto.getEmail())){
            throw new Exception409(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(userSaveRequestDto.getPassword());

        User savedUser = userRepository.save(new User(userSaveRequestDto.getUsername(), userSaveRequestDto.getEmail(), encodedPassword));
        return new UserResponseDto(savedUser);
    }

    // 유저 단건 조회
    @Transactional
    public UserResponseDto getUserById(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(findUser);
    }

    // 유저 모두 조회
    @Transactional
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    // 유저 업데이트
    @Transactional
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));

        String savedPassword = findUser.getPassword();
        String requestPassword = userUpdateRequestDto.getPassword();
        if(!passwordEncoder.matches(requestPassword, savedPassword)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }

        findUser.updateUsername(userUpdateRequestDto.getUsername());

        entityManager.flush();

        return new UserResponseDto(findUser);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long userId, UserDeleteRequestDto userDeleteRequestDto){
        String savedPassword = userRepository.findPasswordByUserId(userId).orElseThrow(() -> new Exception404(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(userDeleteRequestDto.getPassword(), savedPassword)){
            throw new Exception401(ErrorCode.INVALID_PASSWORD);
        }

        userRepository.deleteById(userId);
    }
}
