package fr.esir.jxc.examples.controller;

import java.util.ArrayList;
import java.util.List;

import fr.esir.jxc.examples.exception.InvalidParameterException;
import fr.esir.jxc.examples.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

  /*private final UserService userService;

  public UserController(@Autowired UserService userService) {
    this.userService = userService;
  }*/

  public UserController() {
  }

  @GetMapping
  public List<User> listUsers(@RequestParam(defaultValue="20") Integer size, @RequestParam(defaultValue="1") Integer page) {
    // Validate input data
    if (size < 0 || page < 0) {
      throw new InvalidParameterException();
    }

    List<User> allUsers = new ArrayList<>();
    allUsers.add(new User("1", "username", "password", "email", "photoUrl", "bio", null, new ArrayList<>()));

    return allUsers;
    /*final Iterable<User> users = this.userService.findAll(
      PageRequest.of(Optional.ofNullable(page).orElse(1), Optional.ofNullable(size).orElse(20))
    );
    return StreamSupport.stream(users.spliterator(), false)
      .collect(Collectors.toList());*/
  }


}
