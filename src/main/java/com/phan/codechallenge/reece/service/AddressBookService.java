package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;
    private final UserService userService;

    public AddressBook create(String username, String bookName) {
        return addressBookRepository.findByUserAndName(username, bookName)
                .orElseGet(() -> {
                    User user = userService.getByName(username);
                    AddressBook addressBook = AddressBook.builder()
                            .name(bookName)
                            .user(user)
                            .build();
                    if (null == user.getAddressBooks()) {
                        user.setAddressBooks(new ArrayList<>());
                    }
                    user.getAddressBooks().add(addressBook);
                    addressBookRepository.save(addressBook);
                    return addressBook;
                });
    }

    void delete() {

    }

    void findByUser() {

    }
}
