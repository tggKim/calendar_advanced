package org.example.calendar_advanced.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.dto.UserSaveRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto saveUser(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto){
        return userService.saveUser(userSaveRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserByUserId(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto){
        userService.deleteUser(userId, userDeleteRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
