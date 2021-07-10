package component.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.service.AddressBookService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentTest
public class AddressBookServiceTest {
    @Autowired
    AddressBookService addressBookService;
    @Autowired
    AddressBookRepository addressBookRepository;
    @Autowired
    UserRepository userRepository;

    String userName = "mickey mouse";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        addressBookRepository.deleteAll();
    }

    @Test
    void create_multipleBooks(TestInfo testInfo) {
        for (int i = 1; i < 5; i++) {
            String bookName = testInfo.getDisplayName() + i;
            AddressBook addressBook = addressBookService.create(userName, bookName);

            assertEquals(1, userRepository.count());
            assertEquals(i, addressBookRepository.count());

            assertNotNull(addressBook);
            assertEquals(bookName, addressBook.getName());
            assertEquals(userName, addressBook.getUser().getName());
            assertEquals(i, addressBook.getUser().getAddressBooks().size());
        }
    }
}
