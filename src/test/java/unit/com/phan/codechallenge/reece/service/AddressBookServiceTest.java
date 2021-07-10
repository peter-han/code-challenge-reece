package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.User;
import com.phan.codechallenge.reece.service.AddressBookService;
import com.phan.codechallenge.reece.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
    @Mock
    private UserService userService;

    String userName = "mickey mouse";
    User user = User.builder().name(userName).addressBooks(new ArrayList<>()).build();

    @Test
    void create_newUser(TestInfo testInfo) {
        when(addressBookRepository.findByUserAndName(anyString(), anyString())).thenReturn(Optional.empty());
        when(userService.getByName(anyString())).thenReturn(user);

        AddressBook addressBook = addressBookService.create(userName, testInfo.getDisplayName());

        assertNotNull(addressBook);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
        assertEquals(userName, addressBook.getUser().getName());
        assertEquals(1, addressBook.getUser().getAddressBooks().size());
    }

    @Test
    void createAddressBook_exist(TestInfo testInfo) {
        AddressBook addressBook = AddressBook.builder().name(testInfo.getDisplayName()).build();
        when(addressBookRepository.findByUserAndName(anyString(), anyString())).thenReturn(Optional.of(addressBook));

        AddressBook book = addressBookService.create("hello kitty", "my first address book");

        assertNotNull(book);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
    }

    @Test
    void delete() {
    }
}