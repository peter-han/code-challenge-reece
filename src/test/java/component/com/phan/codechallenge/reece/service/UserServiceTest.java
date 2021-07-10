package component.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import com.phan.codechallenge.reece.service.UserService;
import component.com.phan.codechallenge.reece.ComponentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void findByName_duplicate() {
        assertEquals(0, userRepository.count());

        String userName = "mickey mouse";

        for (int i = 1; i < 5; i++) {
            User user = userService.getByName(userName);
            assertNotNull(user);
            assertEquals(userName, user.getName());
            assertEquals(1, userRepository.count());
        }
    }
}