package fr.esir.jxc.examples.common.models;

import java.util.List;

import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName="user", type="user")
public class User {

  String id;
  String username;
  String password;
  String email;
  String photoUrl;
  String bio;
  @Field(type = FieldType.Nested, includeInParent = true)
  Address address;
  List<String> friendsId;

  @PersistenceConstructor
  public User(String id, String username, String password, String email, String photoUrl, String bio, Address address, List<String> friendsId) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.photoUrl = photoUrl;
    this.bio = bio;
    this.address = address;
    this.friendsId = friendsId;
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
