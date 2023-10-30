package apijunit.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import apijunit.services.exceptions.DataIntegratyViolationException;
import apijunit.services.exceptions.ObjectNotFoundException;

public class ExceptionsHandlerTest {

  @InjectMocks
  private ExceptionsHandler exceptionsHandler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
    ResponseEntity<StandardError> response = exceptionsHandler
        .objectNotFound(new ObjectNotFoundException("Usuario não existe!"), new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals("Usuario não existe!", response.getBody().getError());
    assertEquals(404, response.getBody().getStatus());
    assertNotEquals("user/2", response.getBody().getPath());
    assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
  }

  @Test
  void whenDataIntegratyViolationExceptionThenReturnAResponseEntity() {
    ResponseEntity<StandardError> response = exceptionsHandler
        .DataIntegratyViolationException(new DataIntegratyViolationException("E-mail já cadastrado!"),
            new MockHttpServletRequest());

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(StandardError.class, response.getBody().getClass());
    assertEquals("E-mail já cadastrado!", response.getBody().getError());
    assertEquals(400, response.getBody().getStatus());
  }
}
