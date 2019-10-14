package fr.esir.jxc.examples.command.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerPortCustomizer extends fr.esir.jxc.examples.common.config.ServerPortCustomizer {

  public ServerPortCustomizer(@Value("${writeApi.port}") int port) {
    super(port);
  }

}
