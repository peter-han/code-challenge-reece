package com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.model.AddressBook;
import com.phan.codechallenge.reece.model.Contact;
import com.phan.codechallenge.reece.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
public class AddressBookService {

    public void addContracts(Contact... contacts) {

        if (CollectionUtils.isEmpty(Arrays.asList(contacts))) {
            log.warn("message=\"cannot add new contracts as null value\"");
            throw new IllegalArgumentException();
        }

        for (Contact contact : contacts) {
            RepositoryFacade.CONTACT_REPOSITORY.save(contact);
            log.debug("have added a new contact.");
        }
    }

    public void deleteContracts(Contact... contacts) {
        if (CollectionUtils.isEmpty(Arrays.asList(contacts))) {
            String msg = "cannot add new contracts as null value";
            log.warn(msg);
            throw new IllegalArgumentException(msg);
        }

        for (Contact contact : contacts) {
            RepositoryFacade.CONTACT_REPOSITORY.delete(contact);
            log.debug("message=\"have deleted a contact.\"");
        }
    }

    public void createAddressBook(String addressBookName) {
        RepositoryFacade.ADDRESS_BOOK_REPOSITORY.saveByName(addressBookName);
        log.debug("message=\"have created an address book\"");
    }

    public void deleteAddressBook(String addressBookName) {
        AddressBook addressBook = RepositoryFacade.ADDRESS_BOOK_REPOSITORY.findByName(addressBookName);
        if (null != addressBook) {
            RepositoryFacade.ADDRESS_BOOK_REPOSITORY.delete(addressBook);
        } else {
            String msg = "try to delete a unknown address book";
            log.warn(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    public List<Contact> getContactsByAddressBook(String addressBook) {
        log.debug("get contacts by address book");
        List<Contact> contacts = RepositoryFacade.CONTACT_REPOSITORY.getAllContactByAddressBook(addressBook);

        printContacts(contacts);

        return contacts;
    }

    public Set<Contact> getUniqueSetContacts() {
        log.debug("get unique set of contacts");
        Set<Contact> contacts = RepositoryFacade.ADDRESS_BOOK_REPOSITORY
                .getAll()
                .stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .distinct()
                .collect(toSet());

        printContacts(contacts);

        return contacts;
    }

    public String printContacts(Collection<Contact> contacts) {
        String output = String.join("\n", contacts.stream().map(Contact::toString).collect(Collectors.toList()));
        log.info(output);

        return output;
    }
}
