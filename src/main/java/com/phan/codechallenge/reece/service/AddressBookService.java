package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;
    private final UserService userService;

    public void create(String username, String bookName) {


        AddressBook.builder()
                .name(bookName)
                .build();
        addressBookRepository.save(null);
    }

    public void delete() {

    }
}
