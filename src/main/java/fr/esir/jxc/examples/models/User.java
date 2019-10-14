package fr.esir.jxc.examples.models;

import java.util.List;

import lombok.Value;

@Value
public class User {

  String id;
  String username;
  String password;
  String email;
  String photoUrl;
  String bio;
  Address address;
  List<String> friendsId;

}
