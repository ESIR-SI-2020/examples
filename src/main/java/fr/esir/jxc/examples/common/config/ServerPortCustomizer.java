package fr.esir.jxc.examples.common.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public abstract class ServerPortCustomizer
  implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

  private final int port;

  public ServerPortCustomizer(int port) {
    this.port = port;
  }

  @Override
  public void customize(ConfigurableWebServerFactory factory) {
    factory.setPort(port);
  }

}
