package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByName(String name) {
        Optional<User> user = userRepository.findByName(name);

        return user.orElseGet(() -> userRepository.save(User.builder()
                .name(name)
                .build()));
    }
}
