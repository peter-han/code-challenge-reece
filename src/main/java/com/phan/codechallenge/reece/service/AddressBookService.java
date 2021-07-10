package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;


}
