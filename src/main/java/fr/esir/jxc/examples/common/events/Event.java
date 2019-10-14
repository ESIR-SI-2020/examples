package fr.esir.jxc.examples.common.events;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.esir.jxc.examples.common.utils.Json;
import lombok.Value;

@Value
public class Event {

  String eventName;
  String body;

  public static Optional<Event> of(Object event) {
    try {
      return Optional.of(new Event(event.getClass().getSimpleName(), Json.MAPPER.writeValueAsString(event)));
    } catch (JsonProcessingException e) {
      // TODO handle the error precisely, maybe retry
      e.printStackTrace();
      return Optional.empty();
    }
  }

}
