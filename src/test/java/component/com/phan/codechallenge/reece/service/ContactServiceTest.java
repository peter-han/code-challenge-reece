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

    @BeforeEach
    void setUp(TestInfo testInfo) {
        contactRepository.deleteAll();
        addressBookRepository.deleteAll();

        request.setBookName(testInfo.getDisplayName());
        addressBookService.save(userName, testInfo.getDisplayName());
    }

    @Test
    void saveContact() {
        assertEquals(0, contactRepository.count());

        contactService.saveContact(request);

        assertEquals(1, contactRepository.count());

        List<AddressBook> books = addressBookRepository.findAll();
        assertEquals(1, books.size());
        assertEquals(1, books.get(0).getContacts().size());

        Contact contactInDB = books.get(0).getContacts().get(0);
        assertEquals(request.getPhone(), contactInDB.getPhone());
        assertEquals(request.getContactName(), contactInDB.getName());
    }

    @Test
    void deleteContact() {
    }
}