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
import org.example.calendar_advanced.domain.user.dto.UserSaveRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto){
        return new ResponseEntity<>(userService.saveUser(userSaveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest httpServletRequest){

        Long sessionUserId = getUserIdBySession(httpServletRequest);

        return new ResponseEntity<>(userService.updateUser(userId, sessionUserId, userUpdateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId, @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long sessionUserId = getUserIdBySession(request);

        userService.deleteUser(userId, sessionUserId, userDeleteRequestDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/api/logout");
        dispatcher.forward(request, response);
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Long userId = null;
        if(session != null && session.getAttribute("userId") != null){
            userId = (Long) session.getAttribute("userId");
        }
        return userId;
    }

}
