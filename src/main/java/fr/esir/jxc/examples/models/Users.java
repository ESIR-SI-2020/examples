package fr.esir.jxc.examples.models;

import fr.esir.jxc.examples.exception.InvalidParameterException;

public final class Users {

  public static void validateUserCreationRequest(CreateUserRequest user) {
    if (user.getUsername() == null
      || user.getEmail() == null
      || user.getPassword() == null
      || user.getPassword().length() < 8
    ) {
      throw new InvalidParameterException();
    }
  }

}
