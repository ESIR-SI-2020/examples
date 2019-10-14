package fr.esir.jxc.examples.command.controllers;

import fr.esir.jxc.examples.command.models.CreateUserRequest;
import fr.esir.jxc.examples.command.models.Users;
import fr.esir.jxc.examples.command.services.UserWriteService;
import fr.esir.jxc.examples.common.exception.InvalidParameterException;
import fr.esir.jxc.examples.command.services.UserReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
