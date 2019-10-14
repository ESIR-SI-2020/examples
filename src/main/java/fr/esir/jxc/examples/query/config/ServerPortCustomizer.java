package fr.esir.jxc.examples.query.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerPortCustomizer extends fr.esir.jxc.examples.common.config.ServerPortCustomizer {

  public ServerPortCustomizer(@Value("${readApi.port}") int port) {
    super(port);
  }

}