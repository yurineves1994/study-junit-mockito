package apijunit.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import apijunit.entities.User;
import apijunit.entities.dtos.UserDTO;
import apijunit.services.UserService;

@SpringBootTest
public class UserControllerTest {

  private static final Integer ID = 1;
  private static final String NAME = "Yuri Neves";
  private static final String EMAIL = "yurineves1934@gmail.com";
  private static final String PASSWORD = "1234";

  @InjectMocks
  private UserController controller;

  @Mock
  private UserService service;

  @Mock
  private ModelMapper mapper;

  private User user;
  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void createUser() {

  }

  @Test
  void delete() {

  }

  @Test
  void whenFindAllThenReturnAListOfUserDTO() {
    when(service.findAll()).thenReturn(List.of(user));
    when(mapper.map(any(), any())).thenReturn(userDTO);

    ResponseEntity<List<UserDTO>> response = controller.findAll();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(ArrayList.class, response.getBody().getClass());
    assertEquals(UserDTO.class, response.getBody().get(0).getClass());

    assertEquals(ID, response.getBody().get(0).getId());
    assertEquals(NAME, response.getBody().get(0).getName());
    assertEquals(EMAIL, response.getBody().get(0).getEmail());
    assertEquals(PASSWORD, response.getBody().get(0).getPassword());
  }

  @Test
  void whenFindByIdThenReturnUserDTO() {
    when(service.findById(anyInt())).thenReturn(user);
    when(mapper.map(any(), any())).thenReturn(userDTO);

    ResponseEntity<UserDTO> response = controller.findById(ID);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(ResponseEntity.class, response.getClass());
    assertEquals(UserDTO.class, response.getBody().getClass());

    assertEquals(ID,  response.getBody().getId());
    assertEquals(NAME,  response.getBody().getName());
    assertEquals(EMAIL,  response.getBody().getEmail());
    assertEquals(PASSWORD,  response.getBody().getPassword());
  }

  @Test
  void upgradeUser() {

  }

  private void startUser() {
    user = new User(ID, NAME, EMAIL, PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
  }
}
