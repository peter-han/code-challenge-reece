package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntitlementService {

    private final AddressBookRepository addressBookRepository;

    public AddressBook entitlementCheck(String userName, String bookName) {
        return addressBookRepository.findByUserNameAndName(userName, bookName)
                .orElseThrow(IllegalArgumentException::new);
    }
}
