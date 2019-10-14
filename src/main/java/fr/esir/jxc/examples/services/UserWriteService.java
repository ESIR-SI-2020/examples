package fr.esir.jxc.examples.services;

import fr.esir.jxc.examples.models.CreateUserRequest;
import fr.esir.jxc.examples.models.events.UserCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWriteService {

  private final KafkaProducer kafkaProducer;

  public UserWriteService(@Autowired KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  public void create(CreateUserRequest user) {
    UserCreated userCreated = UserCreated.of(user);
    this.kafkaProducer.produceObject(userCreated);
  }

}
