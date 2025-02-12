package org.example.calendar_advanced.domain.user.service;

import org.assertj.core.api.Assertions;
import org.example.calendar_advanced.domain.user.dto.UserDeleteRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserResponseDto;
import org.example.calendar_advanced.domain.user.dto.UserCreateRequestDto;
import org.example.calendar_advanced.domain.user.dto.UserUpdateRequestDto;
import org.example.calendar_advanced.global.error.exception.Exception401;
import org.example.calendar_advanced.global.error.exception.Exception404;
import org.example.calendar_advanced.global.error.exception.Exception409;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional
    void userSaveAndFindTest(){
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");

        // 유저 저장 테스트
        UserResponseDto savedUser = userService.createUser(userCreateRequestDto);
        Assertions.assertThat(savedUser.getUsername()).isEqualTo("테스트 유저");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("test@test.com");

        // 유저 단일 조회 테스트
        UserResponseDto findUser = userService.findUserById(savedUser.getUserId());
        Assertions.assertThat(findUser.getUserId()).isEqualTo(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo(savedUser.getUsername());
        Assertions.assertThat(findUser.getEmail()).isEqualTo(savedUser.getEmail());

        // 동일한 이메일로 로그인시 예외 테스트
        Assertions.assertThatThrownBy(() -> userService.createUser(userCreateRequestDto)).isInstanceOf(Exception409.class);

        // 유저 삭제하고 조회했을때 예외 테스트
        userService.deleteUser(savedUser.getUserId(), new UserDeleteRequestDto("1234"));
        Assertions.assertThatThrownBy(() -> userService.findUserById(savedUser.getUserId())).isInstanceOf(Exception404.class);

    }

    @Test
    @Transactional
    void findAllUserTest(){
        UserCreateRequestDto userCreateRequestDto1 = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");
        UserCreateRequestDto userCreateRequestDto2 = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");
        UserCreateRequestDto userCreateRequestDto3 = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");

        userService.createUser(userCreateRequestDto1);
        userService.createUser(userCreateRequestDto2);
        userService.createUser(userCreateRequestDto3);

        List<UserResponseDto> userResponseDtos = userService.findAllUsers();

        // 3개 저장했으므로 리스트의 길이가 3임을 테스트
        Assertions.assertThat(userResponseDtos.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void userUpdateTest(){
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");

        // 유저를 저장
        UserResponseDto savedUser = userService.createUser(userCreateRequestDto);

        // 잘못된 비밀번호로 입력시 예외 테스트
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto("업데이트 유저", "0000");
        Assertions.assertThatThrownBy(() -> userService.updateUser(savedUser.getUserId(), userUpdateRequestDto)).isInstanceOf(Exception401.class);

        // 올바른 비밀번호로 입력시 업데이트 되는지 테스트
        UserUpdateRequestDto userUpdateRequestDto2 = new UserUpdateRequestDto("업데이트 유저", "1234");
        userService.updateUser(savedUser.getUserId(), userUpdateRequestDto2);
        UserResponseDto findUser = userService.findUserById(savedUser.getUserId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo("업데이트 유저");

        // 존재하지 않는 userId이면 예외 터지는지 테스트
        userService.deleteUser(savedUser.getUserId(), new UserDeleteRequestDto("1234"));
        Assertions.assertThatThrownBy(() -> userService.updateUser(savedUser.getUserId(), new UserUpdateRequestDto("업데이트 유저", "1234"))).isInstanceOf(Exception404.class);
    }

    @Test
    @Transactional
    void userDeleteTest(){
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto("테스트 유저", "test@test.com", "1234");

        // 유저를 저장
        UserResponseDto savedUser = userService.createUser(userCreateRequestDto);

        // 틀린 비밀번호로 삭제시 예외 터지는지 테스트
        UserDeleteRequestDto userDeleteRequestDto1 = new UserDeleteRequestDto("0000");
        Assertions.assertThatThrownBy(() -> userService.deleteUser(savedUser.getUserId(), userDeleteRequestDto1)).isInstanceOf(Exception401.class);

        // 올바른 비밀번호로 삭제한 뒤 똑같은 userId로 삭제하면 예외가 터지는지 테스트
        UserDeleteRequestDto userDeleteRequestDto2 = new UserDeleteRequestDto("1234");
        userService.deleteUser(savedUser.getUserId(), userDeleteRequestDto2);
        Assertions.assertThatThrownBy(() -> userService.deleteUser(savedUser.getUserId(), userDeleteRequestDto2)).isInstanceOf(Exception404.class);
    }


}