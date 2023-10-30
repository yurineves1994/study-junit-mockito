package apijunit.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import apijunit.entities.User;
import apijunit.repositories.UserRepository;

@Configuration
@Profile("test")
public class LocalConfig {

  @Autowired
  private UserRepository repository;

  @Bean
  public void startDB() {
    User u1 = new User(1, "XXXXXXXX", "xxxx@xxx.com", "12345");
    User u2 = new User(2, "YYYYYYYY", "yyyy@yyy.com", "12345");

    repository.saveAll(List.of(u1, u2));
  }

}
