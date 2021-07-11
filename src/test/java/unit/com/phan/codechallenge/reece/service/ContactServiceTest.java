package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import com.phan.codechallenge.reece.service.AddressBookService;
import com.phan.codechallenge.reece.service.ContactService;
import com.phan.codechallenge.reece.service.EntitlementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private EntitlementService entitlementService;
    @Mock
    private AddressBookService addressBookService;

    String userName = "mickey mouse";
    String contactName = "hello kitty";
    Integer phone = 123456789;
    ContactRequest request = ContactRequest.builder()
            .userName(userName)
            .contactName(contactName)
            .phone(phone)
            .build();
    AddressBook addressBook;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        request.setBookName(testInfo.getDisplayName());

        addressBook = AddressBook.builder()
                .name(testInfo.getDisplayName())
                .userName(userName)
                .build();
    }

    @Test
    void saveContact_notEntitled() {
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.saveContact(request));
    }

    @Test
    void saveContact_Entitled_new() {
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);

        contactService.saveContact(request);
        assertEquals(1, addressBook.getContacts().size());
        verify(contactRepository, times(1)).findByBookAndContactName(anyString(), anyString());
    }

    @Test
    void saveContact_Entitled_update() {
        Contact contact = Contact.builder()
                .addressBook(addressBook)
                .name(contactName)
                .phone(987654321)
                .build();
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);
        when(contactRepository.findByBookAndContactName(eq(request.getBookName()), eq(request.getContactName())))
                .thenReturn(Optional.ofNullable(contact));

        contactService.saveContact(request);
        assertEquals(phone, contact.getPhone());
        assertNull(addressBook.getContacts()); // mock data, didn't set, and update won't touch address book
    }

    @Test
    void saveContact_Entitled_addMore() {
        Contact contact = Contact.builder()
                .name(userName)
                .phone(987654321)
                .build();
        addressBook.setContacts(new ArrayList<>(Collections.singletonList(contact)));
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);
        assertEquals(1, addressBook.getContacts().size());

        contactService.saveContact(request);

        assertEquals(2, addressBook.getContacts().size());
    }

    @Test
    void deleteContact_notEntitled() {
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> contactService.deleteContact(request));
    }

    @Test
    void deleteContact_entitled_notFound() {
        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);
        when(contactRepository.findByBookAndContactName(eq(request.getBookName()), eq(request.getContactName())))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> contactService.deleteContact(request));
    }

    @Test
    void deleteContact_entitled() {
        Contact contact = Contact.builder()
                .name(userName)
                .phone(987654321)
                .build();
        addressBook.setContacts(new ArrayList<>(Collections.singletonList(contact)));

        when(entitlementService.entitlementCheck(eq(userName), anyString()))
                .thenReturn(addressBook);
        when(contactRepository.findByBookAndContactName(eq(request.getBookName()), eq(request.getContactName())))
                .thenReturn(Optional.ofNullable(contact));

        contactService.deleteContact(request);

        assertEquals(0, addressBook.getContacts().size());
    }

    @Test
    void getContracts_noBook() {
        when(addressBookService.getAddressBooks(userName)).thenReturn(Collections.emptyList());
        assertEquals(0, contactService.getContracts(userName).size());
    }

    @Test
    void getContracts_noContact() {
        addressBook.setContacts(Collections.emptyList());
        when(addressBookService.getAddressBooks(userName)).thenReturn(Collections.singletonList(addressBook));
        assertEquals(0, contactService.getContracts(userName).size());
    }

    @Test
    void getContracts() {
        addressBook.setContacts(IntStream.rangeClosed(1, 5)
                .mapToObj(value -> Contact.builder()
                        .name(userName + value)
                        .addressBook(addressBook)
                        .phone(987654321 + value)
                        .build())
                .collect(Collectors.toList()));

        when(addressBookService.getAddressBooks(userName)).thenReturn(Collections.singletonList(addressBook));
        assertEquals(5, contactService.getContracts(userName).size());
    }

    @Test
    void getContracts_duplicate() {
        Contact contact = Contact.builder()
                .name(userName)
                .addressBook(addressBook)
                .phone(987654321)
                .build();
        addressBook.setContacts(Collections.singletonList(contact));

        AddressBook addressBook1 = this.addressBook.withName("Book2");
        addressBook1.setContacts(Collections.singletonList(contact));

        when(addressBookService.getAddressBooks(userName)).thenReturn(Arrays.asList(addressBook, addressBook1));
        assertEquals(1, contactService.getContracts(userName).size());
    }
}