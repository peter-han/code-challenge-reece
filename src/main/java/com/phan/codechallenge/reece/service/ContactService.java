package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactService {
    private final EntitlementService entitlementService;
    private final ContactRepository contactRepository;

    public void saveContact(ContactRequest request) {
        AddressBook addressBook = entitlementService.entitlementCheck(request.getUserName(), request.getBookName());

        Optional<Contact> contact = contactRepository.findByBookAndContactName(request.getBookName(), request.getContactName());
        if (contact.isPresent()) {
            contact.get().setPhone(request.getPhone());
        } else {
            Contact newContact = Contact.builder()
                    .name(request.getContactName())
                    .phone(request.getPhone())
                    .addressBook(addressBook)
                    .build();

            if (CollectionUtils.isEmpty(addressBook.getContacts())) {
                addressBook.setContacts(new ArrayList<>());
            }

            addressBook.getContacts().add(newContact);
        }
    }

    public void deleteContact(ContactRequest request) {
        AddressBook addressBook = entitlementService.entitlementCheck(request.getUserName(), request.getBookName());

        Contact contact = contactRepository.findByBookAndContactName(request.getBookName(), request.getContactName())
                .orElseThrow(NoSuchElementException::new);

        addressBook.getContacts().remove(contact);
        contactRepository.delete(contact);
    }

}
