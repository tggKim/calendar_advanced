package org.example.calendar_advanced.domain.user.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendar_advanced.domain.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.dto.UserCreateRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.domain.user.service.UserService;
import org.example.calendar_advanced.global.util.SessionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionUtil sessionUtil;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto){
        return new ResponseEntity<>(userService.createUser(userCreateRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = sessionUtil.findUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(userService.updateUser(userId, sessionUserId, userUpdateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long sessionUserId = sessionUtil.findUserIdBySession(request);

        userService.deleteUser(userId, sessionUserId, userDeleteRequestDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/api/logout");
        dispatcher.forward(request, response);
    }

}
