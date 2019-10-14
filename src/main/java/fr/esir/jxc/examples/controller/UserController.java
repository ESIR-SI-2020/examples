package fr.esir.jxc.examples.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.esir.jxc.examples.exception.InvalidParameterException;
import fr.esir.jxc.examples.models.User;
import fr.esir.jxc.examples.models.Users;
import fr.esir.jxc.examples.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

  private final UserService userService;

  public UserController(@Autowired UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User.UserPublicRepresentation> listUsers(@RequestParam(defaultValue="20") Integer size, @RequestParam(defaultValue="1") Integer page) {
    // Validate input data
    if (size < 0 || page < 0) {
      throw new InvalidParameterException();
    }

    return this.userService.all().stream()
      .map(User::toPublicRepresentation)
      .collect(Collectors.toList());
    /*final Iterable<User> users = this.userService.findAll(
      PageRequest.of(Optional.ofNullable(page).orElse(1), Optional.ofNullable(size).orElse(20))
    );
    return StreamSupport.stream(users.spliterator(), false)
      .collect(Collectors.toList());*/
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    Users.validateUserCreationRequest(user);

    if (
      this.userService.findByUsername(user.getUsername()).isPresent()
      || this.userService.findByEmail(user.getEmail()).isPresent()
    ) {
      throw new InvalidParameterException();
    }

    return this.userService.create(user);
  }


}
