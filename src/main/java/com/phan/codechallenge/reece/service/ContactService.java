package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    final ContactRepository contactRepository;


}
