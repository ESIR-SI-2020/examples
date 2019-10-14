package fr.esir.jxc.examples.user_events_handler.services;

import java.util.Collections;

import fr.esir.jxc.examples.common.events.UserCreated;
import fr.esir.jxc.examples.common.models.User;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserEventsHandler {

  private final UserWriteRepository userRepository;

  public UserEventsHandler(@Autowired UserWriteRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void handleUserCreatedEvent(UserCreated userCreated) {
    User newUser = new User(
      userCreated.getId(),
      userCreated.getUsername(),
      userCreated.getPassword(),
      userCreated.getEmail(),
      null,
      null,
      null,
      Collections.emptyList()
    );

    User resultingUser = this.userRepository.save(newUser);
    log.debug("Successfully created user: " + resultingUser.toString());
  }

}
