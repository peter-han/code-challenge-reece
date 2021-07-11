package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final EntitlementService entitlementService;
    private final ContactRepository contactRepository;

    public void saveContact(String userName, String bookName, String contactName, Integer phone) {
        AddressBook addressBook = entitlementService.entitlementCheck(userName, bookName);

        Optional<Contact> contact = contactRepository.findByBookAndContactName(bookName, contactName);
        if (contact.isPresent()) {
            contact.get().setPhone(phone);
        } else {
            Contact newContact = Contact.builder()
                    .name(contactName)
                    .phone(phone)
                    .build();

            addressBook.getContacts().add(newContact);
        }
    }

    public void deleteContact(String userName, String bookName, String contactName, Integer phone) {
        AddressBook addressBook = entitlementService.entitlementCheck(userName, bookName);

//        Optional<Contact> contact = contactRepository.findByBookAndContactName(bookName, contactName)
//                .orElseThrow(() -> {
//                    throw new IllegalArgumentException(String.format("{} doesn't have address book {}", userName, bookName));
//                });

//        addressBook.getContacts().remove();
    }


}
