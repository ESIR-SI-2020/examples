package fr.esir.jxc.examples.user_events_handler.services;

import java.io.IOException;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esir.jxc.examples.common.events.Event;
import fr.esir.jxc.examples.common.events.UserCreated;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserEventsListener implements MessageListener<String, Event> {

  private final ObjectMapper objectMapper;
  private final UserEventsHandler eventsHandler;

  public UserEventsListener(@Autowired ObjectMapper objectMapper, @Autowired UserEventsHandler eventsHandler) {
    this.objectMapper = objectMapper;
    this.eventsHandler = eventsHandler;
  }

  @Override
  public void onMessage(ConsumerRecord<String, Event> data) {
    routeEvent(data.value());
  }

  private void routeEvent(Event event) {
    switch(event.getEventName()) {
      case "UserCreated":
        handleEvent(event, UserCreated.class, this.eventsHandler::handleUserCreatedEvent);
        break;
      default:
        log.warn("Received an unknown event: " + event.toString());
        break;
    }
  }

  private <T> void handleEvent(Event event, Class<T> tClass, Consumer<T> handler) {
    try {
      final T parsedBody = this.objectMapper.readValue(event.getBody(), tClass);
      handler.accept(parsedBody);
    } catch (IOException e) {
      // TODO: handle exceptions more precisely
      log.error(e.getMessage(), e);
    }
  }

}
