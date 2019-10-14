package fr.esir.jxc.examples.models;

import java.util.List;
import java.util.UUID;

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

  public User generateId() {
    return new User(UUID.randomUUID().toString(), username, password, email, photoUrl, bio, address,  friendsId);
  }

  public UserPublicRepresentation toPublicRepresentation() {
    return new UserPublicRepresentation(id, username, email, photoUrl, bio, address, friendsId);
  }

  @Value
  public static class UserPublicRepresentation {
    String id;
    String username;
    String email;
    String photoUrl;
    String bio;
    Address address;
    List<String> friendsId;
  }

}
