package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByName(String name) {
        return userRepository.findByName(name)
                .orElseGet(() -> userRepository.save(User.builder()
                        .name(name)
                        .build()));
    }
}