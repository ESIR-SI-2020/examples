package fr.esir.jxc.examples.models.events;

import java.util.UUID;

import fr.esir.jxc.examples.models.CreateUserRequest;
import fr.esir.jxc.examples.services.Security;
import lombok.Value;

@Value
public class UserCreated {

  String id;
  String username;
  String password;
  String email;

  public static UserCreated of(CreateUserRequest user) {
    return new UserCreated(
      UUID.randomUUID().toString(),
      user.getUsername(),
      Security.hash(user.getPassword()),
      user.getEmail()
    );
  }

}
