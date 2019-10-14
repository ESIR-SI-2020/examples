package fr.esir.jxc.examples.models;

import lombok.Value;

@Value
public class CreateUserRequest {

  String username;
  String password;
  String email;

}
