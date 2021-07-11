package component.com.phan.codechallenge.reece.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phan.codechallenge.reece.controller.bean.AddressBookRequest;
import com.phan.codechallenge.reece.repository.AddressBookRepository;
import com.phan.codechallenge.reece.repository.entity.AddressBook;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
@AutoConfigureMockMvc
class AddressBookControllerTest {

    static final String ENDPOINT = "/v1/addressbook";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AddressBookRepository addressBookRepository;

    String userName = "mickey mouse";

    AddressBookRequest request;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        assertEquals(0, addressBookRepository.count());
        request = AddressBookRequest.builder()
                .userName(userName)
                .bookName(testInfo.getDisplayName())
                .build();
    }

    @Test
    void createByUsers() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, addressBookRepository.count());
    }

    @Test
    void createByUsers_noBody_400() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Disabled
    @Test
    void createByUsers_invalidBody_4xx() throws Exception {
        AddressBookRequest request = AddressBookRequest.builder().build();

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteByUsers() throws Exception {
        AddressBook addressBook = AddressBook.builder()
                .name(request.getBookName())
                .userName(request.getUserName())
                .build();
        addressBookRepository.save(addressBook);
        assertEquals(1, addressBookRepository.count());

        mockMvc.perform(delete(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful());

        assertEquals(0, addressBookRepository.count());
    }

    @Test
    void retrieveByUsers() throws Exception {
        AddressBook addressBook = AddressBook.builder()
                .name(request.getBookName())
                .userName(request.getUserName())
                .build();
        addressBookRepository.save(addressBook);
        assertEquals(1, addressBookRepository.count());

        mockMvc.perform(get(ENDPOINT + "/" + userName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}