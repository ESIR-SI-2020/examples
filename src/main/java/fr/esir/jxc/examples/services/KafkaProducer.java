package fr.esir.jxc.examples.services;

import fr.esir.jxc.examples.models.events.Event;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

  public void produce(Event event) {
    System.out.println(event.toString());
  }

}
