package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.ComponentTest;
import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({
            "Peter Han, 1",
            "mickey mouse, 2",
            "mickey mouse, 2", // the same user again
    })
    void findByName(String name, int totalSize) {
        User user = userService.getByName(name);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(totalSize, userRepository.count());
    }
}