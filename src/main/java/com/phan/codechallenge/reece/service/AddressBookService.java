package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;

    public AddressBook save(String userName, String bookName) {
        return addressBookRepository.findByUserNameAndName(userName, bookName)
                .orElseGet(() -> {
                    // TODO race condition in save
                    AddressBook addressBook = AddressBook.builder()
                            .name(bookName)
                            .userName(userName)
                            .build();
                    addressBookRepository.save(addressBook);
                    return addressBook;
                });
    }

    public void delete(String userName, String bookName) {
        Optional<AddressBook> addressBook = addressBookRepository.findByUserNameAndName(userName, bookName);
        if (!addressBook.isPresent()) {
            throw new IllegalArgumentException(String.format("{} doesn't have address book {}", userName, bookName));
        }

        addressBookRepository.delete(addressBook.get());
    }

    public void findByUser() {
    }
}
