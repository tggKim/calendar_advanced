package org.example.calendar_advanced.user.repository;

import org.assertj.core.api.Assertions;
import org.example.calendar_advanced.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void getPasswordByUserIdTest(){
        User user = new User("테스트유저", "test@test.com", "1234");

        User savedUser = userRepository.save(user);

        String findPassword = userRepository.findPasswordByUserId(savedUser.getUserId()).get();

        Assertions.assertThat(findPassword).isEqualTo("1234");
    }
}