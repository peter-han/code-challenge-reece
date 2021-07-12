package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.service.AddressBookService;
import com.phan.codechallenge.reece.service.EntitlementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private AddressBookRepository addressBookRepository;
    @Mock
    private EntitlementService entitlementService;

    String userName = "mickey mouse";

    @Test
    void createAddressBook_new(TestInfo testInfo) {
        when(addressBookRepository.findByUserNameAndName(anyString(), anyString())).thenReturn(Optional.empty());

        AddressBook addressBook = addressBookService.save(userName, testInfo.getDisplayName());

        assertNotNull(addressBook);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
        assertEquals(userName, addressBook.getUserName());

        verify(addressBookRepository, times(1)).save(any());
    }

    @Test
    void createAddressBook_exist(TestInfo testInfo) {
        AddressBook addressBook = AddressBook.builder()
                .name(testInfo.getDisplayName())
                .userName(userName)
                .build();
        when(addressBookRepository.findByUserNameAndName(userName, testInfo.getDisplayName())).thenReturn(Optional.of(addressBook));

        AddressBook book = addressBookService.save(userName, testInfo.getDisplayName());

        assertNotNull(book);
        assertEquals(testInfo.getDisplayName(), addressBook.getName());
        assertEquals(userName, addressBook.getUserName());

        verify(addressBookRepository, times(0)).save(any());
    }

    @Test
    void delete_success(TestInfo testInfo) {
        AddressBook addressBook = AddressBook.builder()
                .name(testInfo.getDisplayName())
                .userName(userName)
                .build();
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);

        addressBookService.delete(userName, testInfo.getDisplayName());

        verify(addressBookRepository, times(1)).delete(any());
    }

    @Test
    void delete_notFound(TestInfo testInfo) {
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> addressBookService.delete(userName, testInfo.getDisplayName()));
    }
}