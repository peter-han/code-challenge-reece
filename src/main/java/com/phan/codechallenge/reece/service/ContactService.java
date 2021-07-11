package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.controller.bean.ContactResponse;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // FIXME notes, not in prod
public class ContactService {
    private final AddressBookService addressBookService;
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

    public Set<ContactResponse> getContracts(String userName, String bookName) {
        Optional<AddressBook> addressBook = addressBookService.getAddressBook(userName, bookName);

        return addressBook.map(book -> book
                .getContacts().stream()
                .map(contact -> ContactResponse.builder()
                        .addressBook(contact.getAddressBook().getName())
                        .name(contact.getName())
                        .phone(contact.getPhone())
                        .build())
                .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

    public Set<ContactResponse> getContracts(String userName) {
        List<AddressBook> addressBooks = addressBookService.getAddressBooks(userName);

        if (CollectionUtils.isEmpty(addressBooks)) {
            return Collections.emptySet();
        }

        return addressBooks.stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .map(contact -> ContactResponse.builder()
                        .addressBook(contact.getAddressBook().getName())
                        .name(contact.getName())
                        .phone(contact.getPhone())
                        .build())
                .collect(Collectors.toSet());
    }
}
