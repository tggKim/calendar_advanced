package org.example.calendar_advanced.user.service;

import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.user.dto.UserResponseDto;
import org.example.calendar_advanced.user.dto.UserSaveRequestDto;
import org.example.calendar_advanced.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.user.entity.User;
import org.example.calendar_advanced.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 저장
    @Transactional
    public UserResponseDto saveUser(UserSaveRequestDto userSaveRequestDto){
        User savedUser = userRepository.save(userSaveRequestDto.toUser());
        return new UserResponseDto(savedUser);
    }

    // 유저 단건 조회
    @Transactional
    public UserResponseDto getUserById(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("userId에 해당하는 유저가 없습니다."));
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
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("userId에 해당하는 유저가 없습니다."));

        String savedPassword = findUser.getPassword();
        String requestPassword = userUpdateRequestDto.getPassword();
        if(!savedPassword.equals(requestPassword)){
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        findUser.updateUsername(userUpdateRequestDto.getUsername());

        return new UserResponseDto(findUser);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long userId, UserDeleteRequestDto userDeleteRequestDto){
        String savedPassword = userRepository.findPasswordByUserId(userId).orElseThrow(() -> new NoSuchElementException("userId에 해당하는 유저가 없습니다."));

        if(!savedPassword.equals(userDeleteRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        userRepository.deleteById(userId);
    }
}
