package apijunit.services;

import java.util.List;

import apijunit.entities.User;
import apijunit.entities.dtos.UserDTO;

public interface UserService {

  public List<User> findAll();

  public User findById(Integer id);

  public User create(UserDTO dto);

  public User update(UserDTO dto);

}