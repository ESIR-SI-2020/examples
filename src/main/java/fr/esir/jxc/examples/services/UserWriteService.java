package fr.esir.jxc.examples.services;

import fr.esir.jxc.examples.models.CreateUserRequest;
import fr.esir.jxc.examples.models.events.Event;
import fr.esir.jxc.examples.models.events.UserCreated;

import org.springframework.stereotype.Service;

@Service
public class UserWriteService {

  private final KafkaProducer kafkaProducer;

  public UserWriteService(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  public void create(CreateUserRequest user) {
    UserCreated userCreated = UserCreated.of(user);
    Event.of(userCreated)
      .ifPresent(event -> this.kafkaProducer.produce(event));
  }

}
