package unit.com.phan.codechallenge.reece.service;

import com.phan.codechallenge.reece.repository.UserRepository;
import com.phan.codechallenge.reece.repository.entity.User;
import com.phan.codechallenge.reece.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    String userName = "mickey mouse";
    User user = User.builder().name(userName).addressBooks(new ArrayList<>()).build();

    @Test
    void findByName_newUser() {
        when(userRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.getByName(userName);
        assertNotNull(result);
        assertEquals(userName, result.getName());
    }

    @Test
    void findByName_existUser() {
        when(userRepository.findByName(anyString())).thenReturn(Optional.of(user));

        User result = userService.getByName(userName);
        assertNotNull(result);
        assertEquals(userName, result.getName());
    }
}