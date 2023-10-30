package apijunit.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import apijunit.entities.User;
import apijunit.entities.dtos.UserDTO;
import apijunit.repositories.UserRepository;
import apijunit.services.UserService;
import apijunit.services.exceptions.DataIntegratyViolationException;
import apijunit.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private ModelMapper mapper;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Integer id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não existe!"));
  }

  @Override
  public User create(UserDTO dto) {
    findByEmail(dto);
    return userRepository.save(mapper.map(dto, User.class));
  }

  private void findByEmail(UserDTO user) {
    Optional<User> userExists = userRepository.findByEmail(user.getEmail());

    if (userExists.isPresent() && !userExists.get().getId().equals(user.getId())) {
      throw new DataIntegratyViolationException("E-mail já cadastrado!");
    }
  }

  @Override
  public User update(UserDTO user) {
    findByEmail(user);
    return userRepository.save(mapper.map(user, User.class));
  }

  @Override
  public void delete(Integer id) {
    findById(id);
    userRepository.deleteById(id);
  }

}
