package fr.esir.jxc.examples.controller;

import java.util.List;
import java.util.stream.Collectors;

import fr.esir.jxc.examples.exception.InvalidParameterException;
import fr.esir.jxc.examples.models.CreateUserRequest;
import fr.esir.jxc.examples.models.User;
import fr.esir.jxc.examples.models.Users;
import fr.esir.jxc.examples.services.UserReadService;
import fr.esir.jxc.examples.services.UserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

  private final UserReadService userReadService;
  private final UserWriteService userWriteService;

  public UserController(
    @Autowired UserReadService userReadService,
    @Autowired UserWriteService userWriteService
  ) {
    this.userReadService = userReadService;
    this.userWriteService = userWriteService;
  }

  @GetMapping
  public List<User.UserPublicRepresentation> listUsers(@RequestParam(defaultValue="20") Integer size, @RequestParam(defaultValue="1") Integer page) {
    // Validate input data
    if (size < 0 || page < 0) {
      throw new InvalidParameterException();
    }

    return this.userReadService.all().stream()
      .map(User::toPublicRepresentation)
      .collect(Collectors.toList());
    /*final Iterable<User> users = this.userReadService.findAll(
      PageRequest.of(Optional.ofNullable(page).orElse(1), Optional.ofNullable(size).orElse(20))
    );
    return StreamSupport.stream(users.spliterator(), false)
      .collect(Collectors.toList());*/
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody CreateUserRequest user) {
    Users.validateUserCreationRequest(user);

    if (
      this.userReadService.findByUsername(user.getUsername()).isPresent()
      || this.userReadService.findByEmail(user.getEmail()).isPresent()
    ) {
      throw new InvalidParameterException();
    }

    this.userWriteService.create(user);
  }


}
