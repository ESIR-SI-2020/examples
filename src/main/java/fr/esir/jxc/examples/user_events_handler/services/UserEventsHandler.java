package fr.esir.jxc.examples.user_events_handler.services;

import fr.esir.jxc.examples.common.events.UserCreated;
import org.springframework.stereotype.Service;

@Service
public class UserEventsHandler {

  public void handleUserCreatedEvent(UserCreated userCreated) {
    System.out.println("-------------------------------- YEAH <3");
    System.out.println(userCreated.toString());
  }

}
