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

    public AddressBook save(String userName, String bookName) {
        return addressBookRepository.findByUserNameAndName(userName, bookName)
                .orElseGet(() -> {
                    AddressBook addressBook = AddressBook.builder()
                            .name(bookName)
                            .userName(userName)
                            .build();
                    addressBookRepository.save(addressBook);
                    return addressBook;
                });
    }

    void delete() {

    }

    void findByUser() {

    }
}
