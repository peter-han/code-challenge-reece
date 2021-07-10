package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private AddressBookRepository addressBookRepository;

    String userName = "mickey mouse";

    @Test
    void createAddressBook_new(TestInfo testInfo) {
        when(addressBookRepository.findByUserNameAndName(anyString(), anyString())).thenReturn(Optional.empty());

        AddressBook addressBook = addressBookService.save(userName, testInfo.getDisplayName());

        assertNotNull(addressBook);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
        assertEquals(userName, addressBook.getUserName());
    }

    @Test
    void createAddressBook_exist(TestInfo testInfo) {
        AddressBook addressBook = AddressBook.builder().name(testInfo.getDisplayName()).build();
        when(addressBookRepository.findByUserNameAndName(anyString(), anyString())).thenReturn(Optional.of(addressBook));

        AddressBook book = addressBookService.save("hello kitty", "my first address book");

        assertNotNull(book);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
    }

    @Test
    void delete() {
    }
}