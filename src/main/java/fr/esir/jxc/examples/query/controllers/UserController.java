package fr.esir.jxc.examples.query.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
  public List<User.UserPublicRepresentation> listUsers(@RequestParam(defaultValue="20") Integer size, @RequestParam(defaultValue="0") Integer page) {
    // Validate input data
    if (size < 0 || page < 0) {
      throw new InvalidParameterException();
    }

    final Iterable<User> users = this.userReadService.findAll(PageRequest.of(page, size));
    return StreamSupport.stream(users.spliterator(), false)
      .map(User::toPublicRepresentation)
      .collect(Collectors.toList());
  }


}
