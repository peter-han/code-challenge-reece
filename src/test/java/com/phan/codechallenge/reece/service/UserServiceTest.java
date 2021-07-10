package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.ComponentTest;
import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void when_findByName_newUser_then_save() {
        assertEquals(0, userRepository.count());

        User user = userService.getByName("Peter Han");
        assertNotNull(user);
        assertEquals("Peter Han", user.getName());
        assertEquals(1, userRepository.count());

        user = userService.getByName("mickey mouse");
        assertNotNull(user);
        assertEquals("mickey mouse", user.getName());
        assertEquals(2, userRepository.count());
    }

    @Test
    void when_findByName_existUser_then_get() {
        assertEquals(0, userRepository.count());
        String name = "Peter Han";

        User user = userService.getByName(name);
        assertNotNull(user);
        assertEquals(1, userRepository.count());

        user = userService.getByName(name);
        assertNotNull(user);
        assertEquals(1, userRepository.count());
    }
}