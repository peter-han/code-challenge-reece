package component.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import com.phan.codechallenge.reece.service.AddressBookService;
import com.phan.codechallenge.reece.service.ContactService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ComponentTest
class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AddressBookRepository addressBookRepository;
    @Autowired
    AddressBookService addressBookService;

    String userName = "mickey mouse";
    ContactRequest request = ContactRequest.builder()
            .userName(userName)
            .contactName("hello kitty")
            .phone(123456789)
            .build();
    long addressBookId;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        request.setBookName(testInfo.getDisplayName());
        addressBookId = addressBookService.save(userName, testInfo.getDisplayName()).getId();
    }

    @Test
    void saveContact() {
        assertEquals(0, contactRepository.count());

        contactService.saveContact(request);

        assertEquals(1, contactRepository.count());

        List<AddressBook> books = addressBookRepository.findAll();
        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getContacts().size());

        Contact contactInDB = books.get(0).getContacts().iterator().next();
        assertEquals(request.getPhone(), contactInDB.getPhone());
        assertEquals(request.getContactName(), contactInDB.getName());
    }

    @Test
    void deleteContact() {
        assertEquals(0, contactRepository.count());

        contactService.saveContact(request);
        assertEquals(1, addressBookRepository.findById(addressBookId).get().getContacts().size());
        assertEquals(1, contactRepository.count());

        contactService.deleteContact(request);
        assertEquals(0, contactRepository.count());
        assertEquals(0, addressBookRepository.findById(addressBookId).get().getContacts().size());
    }

    @Test
    void getContracts() {
        contactService.saveContact(request);
        contactService.saveContact(request);

        assertEquals(1, contactService.getContracts(userName).size());
    }
}