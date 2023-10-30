package apijunit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import apijunit.entities.User;
import apijunit.repositories.UserRepository;
import apijunit.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

  // private ModelMapper mapper;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    return user.orElse(null);
  }

  public User findById(Integer id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não existe!"));
  }

  // public User createUser(UserDTO dto) {
  //   User user = mapper.map(dto, User.class);
  //   return userRepository.save(user);
  // }

}