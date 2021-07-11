package component.com.phan.codechallenge.reece.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.repository.ContactRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import com.phan.codechallenge.reece.repository.entity.Contact;
import com.phan.codechallenge.reece.service.AddressBookService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Test
    void addContact() throws Exception {
        assertEquals(0, contactRepository.count());

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

        assertEquals(0, contactRepository.count());
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
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/db/testdata/testdata.sql"})
    void retrieveByUser() throws Exception {
        mockMvc.perform(get(ENDPOINT + "/" + userName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    private AddressBook setEntitled() {
        return addressBookService.save(request.getUserName(), request.getBookName());
    }
}