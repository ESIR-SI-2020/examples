package fr.esir.jxc.examples.user_events_handler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerPortCustomizer extends fr.esir.jxc.examples.common.config.ServerPortCustomizer {

  public ServerPortCustomizer(@Value("${userEventsHandler.port}") int port) {
    super(port);
  }

}
