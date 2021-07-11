package unit.com.phan.codechallenge.reece.controller;

import com.phan.codechallenge.reece.controller.GlobalExceptionHandler;
import com.phan.codechallenge.reece.controller.bean.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Mock
    HttpServletRequest request;

    @ParameterizedTest
    @MethodSource("listOf4xxExceptions")
    void handle(Class<Exception> exception) {
        ResponseEntity<ErrorResponse> responseEntity = handler.handle(mock(exception), request);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void handleAll() {
        ResponseEntity<ErrorResponse> responseEntity = handler.handleAll(mock(RuntimeException.class), request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    static Stream<Arguments> listOf4xxExceptions() {
        return Stream.of(
                Arguments.of(IllegalArgumentException.class),
                Arguments.of(HttpMessageNotReadableException.class),
                Arguments.of(HttpMediaTypeNotSupportedException.class)
        );
    }
}