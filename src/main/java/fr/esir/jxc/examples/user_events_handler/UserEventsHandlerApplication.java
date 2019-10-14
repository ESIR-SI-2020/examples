package fr.esir.jxc.examples.user_events_handler;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"fr.esir.jxc.examples.user_events_handler","fr.esir.jxc.examples.common"})
public class UserEventsHandlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserEventsHandlerApplication.class, args);
  }

}
