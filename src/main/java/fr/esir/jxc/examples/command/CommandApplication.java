package fr.esir.jxc.examples.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"fr.esir.jxc.examples.command","fr.esir.jxc.examples.common"})
public class CommandApplication {

  public static void main(String[] args) {
    SpringApplication.run(CommandApplication.class, args);
  }

}
