package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.service.EntitlementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntitlementServiceTest {

    @InjectMocks
    private EntitlementService entitlementService;
    @Mock
    private AddressBookRepository addressBookRepository;

    String userName = "mickey mouse";

    @Test
    void entitlementCheck_success(TestInfo testInfo) {
        when(addressBookRepository.findByUserNameAndName(eq(userName), anyString()))
                .thenReturn(Optional.of(AddressBook.builder().userName(userName).build()));
        AddressBook addressBook = entitlementService.entitlementCheck(userName, testInfo.getDisplayName());

        assertEquals(userName, addressBook.getUserName());
    }

    @Test
    void entitlementCheck_failed(TestInfo testInfo) {
        when(addressBookRepository.findByUserNameAndName(eq(userName), anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> entitlementService.entitlementCheck(userName, testInfo.getDisplayName()));
    }
}