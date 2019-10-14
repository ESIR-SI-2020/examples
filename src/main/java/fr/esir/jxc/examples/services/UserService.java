package fr.esir.jxc.examples.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.esir.jxc.examples.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final List<User> users;

  public UserService() {
    this.users = new ArrayList<>();
  }

  public List<User> all() {
    return this.users;
  }

  public Optional<User> findByUsername(String username) {
    return this.users.stream()
      .filter(user -> user.getUsername().equals(username))
      .findFirst();
  }

  public Optional<User> findByEmail(String email) {
    return this.users.stream()
      .filter(user -> user.getEmail().equals(email))
      .findFirst();
  }

  public User create(User user) {
    final User userCreated = user.generateId();
    this.users.add(userCreated);
    return userCreated;
  }

}
