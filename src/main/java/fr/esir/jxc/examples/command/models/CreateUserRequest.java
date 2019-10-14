package fr.esir.jxc.examples.command.models;

import lombok.Value;

@Value
public class CreateUserRequest {

  String username;
  String password;
  String email;

}
