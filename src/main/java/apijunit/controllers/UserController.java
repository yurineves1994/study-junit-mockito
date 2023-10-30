package apijunit.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apijunit.entities.User;
import apijunit.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService service;

  @Autowired
  private ModelMapper mapper;

  // @PostMapping
  // public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user) {
  // return ResponseEntity.ok().body(service.createUser(user));
  // }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(mapper.map(service.findById(id), User.class));
  }

}
