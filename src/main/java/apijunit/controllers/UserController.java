package apijunit.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apijunit.entities.User;
import apijunit.entities.dtos.UserDTO;
import apijunit.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final String ID = "/{id}";

  @Autowired
  private UserService service;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO user) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
        .buildAndExpand(service.create(user).getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(ID)
  public ResponseEntity<User> upgradeUser(@PathVariable Integer id, @RequestBody @Valid UserDTO user) {
    user.setId(id);

    return ResponseEntity.ok().body(mapper.map(service.update(user), User.class));
  }

  @GetMapping(value = ID)
  public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> users = service.findAll();
    List<UserDTO> userdtos = users.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
    return ResponseEntity.ok().body(userdtos);
  }

  @DeleteMapping(value = ID)
  public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {

    service.delete(id);

    return ResponseEntity.noContent().build();
  }

}
