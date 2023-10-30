package apijunit.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import apijunit.entities.User;
import apijunit.entities.dtos.UserDTO;
import apijunit.repositories.UserRepository;
import apijunit.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

  private static final Integer ID = 1;
  private static final String NAME = "Yuri Neves";
  private static final String EMAIL = "yurineves1934@gmail.com";
  private static final String PASSWORD = "1234";

  @InjectMocks
  private UserServiceImpl service;

  @Mock
  private UserRepository repository;

  @Mock
  private ModelMapper mapper;

  private User user;
  private UserDTO userDTO;
  private Optional<User> optionalUser;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    startUser();
  }

  @Test
  void whenCreateThenReturnSucess() {
    when(repository.save(any())).thenReturn(user);

    User response = service.create(userDTO);

    assertNotNull(response);
    assertEquals(User.class, response.getClass());
    assertEquals(ID, response.getId());
    assertEquals(NAME, response.getName());
    assertEquals(EMAIL, response.getEmail());
    assertEquals(PASSWORD, response.getPassword());
  }

  @Test
  void delete() {

  }

  @Test
  void whenFindAllThenReturnAnListOfUsers() {
    when(repository.findAll()).thenReturn(List.of(user));

    List<User> response = service.findAll();

    assertNotNull(response);
    assertEquals(1, response.size());
    assertEquals(User.class, response.get(0).getClass());
    assertEquals(ID, response.get(0).getId());
    assertEquals(NAME, response.get(0).getName());
    assertEquals(EMAIL, response.get(0).getEmail());
    assertEquals(PASSWORD, response.get(0).getPassword());
  }

  @Test
  void whenFindByIdThenReturnAnUserInstace() {
   when(repository.findById(anyInt())).thenReturn(optionalUser);

    User response = service.findById(ID);

   assertNotNull(response);
   assertEquals(User.class, response.getClass());
   assertEquals(ID, response.getId());
   assertEquals(NAME, response.getName());
   assertEquals(EMAIL, response.getEmail());
  }

  @Test
  void whenFindByIdThenReturnAnObjectNotFoundException() {
   when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Usuario não existe!"));

   try {
    service.findById(ID);
   } catch (Exception ex) {
    assertEquals(ObjectNotFoundException.class, ex.getClass());
    assertEquals("Usuario não existe!", ex.getMessage());
   }
   
  }

  @Test
  void update() {

  }

  private void startUser() {
    user = new User(ID, NAME, EMAIL, PASSWORD);
    userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
  }
}
