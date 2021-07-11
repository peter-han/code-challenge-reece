package component.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.service.AddressBookService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentTest
public class AddressBookServiceTest {
    @Autowired
    AddressBookService service;
    @Autowired
    AddressBookRepository repository;

    String userName = "mickey mouse";

    @Test
    void create_concurrent(TestInfo testInfo) {
        IntStream.rangeClosed(1, 5)
                .parallel()
                .forEach(value -> {
                    String bookName = testInfo.getDisplayName() + value;
                    AddressBook addressBook = service.save(userName, bookName);

                    assertNotNull(addressBook);
                    assertEquals(bookName, addressBook.getName());
                });

        assertEquals(5, repository.count());
    }

    @Test
    void delete(TestInfo testInfo) {
        AddressBook addressBook = AddressBook.builder()
                .name(testInfo.getDisplayName())
                .userName(userName)
                .build();
        repository.save(addressBook);
        assertEquals(1, repository.count());

        service.delete(userName, testInfo.getDisplayName());
        assertEquals(0, repository.count());
    }
}
