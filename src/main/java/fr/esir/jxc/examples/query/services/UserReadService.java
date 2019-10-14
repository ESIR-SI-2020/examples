package fr.esir.jxc.examples.query.services;

import java.util.ArrayList;
import java.util.List;

import fr.esir.jxc.examples.common.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserReadService {

  private final List<User> users;

  public UserReadService() {
    this.users = new ArrayList<>();
  }

  public List<User> all() {
    return this.users;
  }

}
