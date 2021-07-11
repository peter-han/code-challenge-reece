package component.com.phan.codechallenge.reece.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phan.codechallenge.reece.controller.bean.AddressBookRequest;
import com.phan.codechallenge.reece.repository.AddressBookRepository;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
@AutoConfigureMockMvc
class AddressBookControllerTest {

    String endpoint = "/v1/addressbook";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AddressBookRepository addressBookRepository;

    String userName = "mickey mouse";

    @BeforeEach
    void setUp() {
        assertEquals(0, addressBookRepository.count());
    }

    @Test
    void createByUsers(TestInfo testInfo) throws Exception {
        AddressBookRequest request = AddressBookRequest.builder()
                .userName(userName)
                .bookName(testInfo.getDisplayName())
                .build();

        mockMvc.perform(post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful());

        assertEquals(1, addressBookRepository.count());
    }

    @Test
    void createByUsers_noBody_400() throws Exception {
        mockMvc.perform(post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteByUsers() {
    }

    @Test
    void retrieveByUsers() {
    }
}