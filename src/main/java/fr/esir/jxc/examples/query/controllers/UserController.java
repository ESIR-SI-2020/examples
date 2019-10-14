package fr.esir.jxc.examples.query.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esir.jxc.examples.common.exception.InvalidParameterException;
import fr.esir.jxc.examples.common.models.User;
import fr.esir.jxc.examples.query.services.UserReadService;

@RestController("/users")
public class UserController {

  private final UserReadService userReadService;

  public UserController(
    @Autowired UserReadService userReadService
  ) {
    this.userReadService = userReadService;
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


}
