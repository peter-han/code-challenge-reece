package component.com.phan.codechallenge.reece.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import com.phan.codechallenge.reece.service.AddressBookService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
@AutoConfigureMockMvc
class ContactControllerTest {

    static final String ENDPOINT = "/v1/contact";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AddressBookService addressBookService;

    String userName = "mickey mouse";
    ContactRequest request = ContactRequest.builder()
            .userName(userName)
            .bookName("test book")
            .contactName("hello kitty")
            .phone(1234567890)
            .build();

    @BeforeEach
    void setUp(TestInfo testInfo) {
        assertEquals(0, contactRepository.count());
    }

    @Test
    void addContact() throws Exception {
        setEntitled();

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, contactRepository.count());
    }

    @Test
    void addContact_notEntitled_4xx() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is4xxClientError());

        assertEquals(1, contactRepository.count());
    }

    @Test
    void deleteByUser() throws Exception {
        AddressBook addressBook = setEntitled();

        contactRepository.save(Contact.builder()
                .addressBook(addressBook)
                .name(request.getContactName())
                .phone(request.getPhone())
                .build());
        assertEquals(1, contactRepository.count());

        mockMvc.perform(delete(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful());

        assertEquals(0, contactRepository.count());
    }

    @Test
    void deleteByUser_notEntitled() throws Exception {
        mockMvc.perform(delete(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is4xxClientError());

        assertEquals(1, contactRepository.count());
    }

    @Test
    void retrieveByUser() {
    }

    private AddressBook setEntitled() {
        return addressBookService.save(request.getUserName(), request.getBookName());
    }
}