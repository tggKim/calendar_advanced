package org.example.calendar_advanced.user.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.user.dto.UserResponseDto;
import org.example.calendar_advanced.user.dto.UserSaveRequestDto;
import org.example.calendar_advanced.user.entity.User;
import org.example.calendar_advanced.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto){
        User savedUser = userRepository.save(userSaveRequestDto.toUser());
        return new UserResponseDto(savedUser);
    }

    public UserResponseDto getUserByUserId(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("userId에 해당하는 유저가 없습니다"));
        return new UserResponseDto(findUser);
    }
}
