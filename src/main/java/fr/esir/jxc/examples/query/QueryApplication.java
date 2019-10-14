package fr.esir.jxc.examples.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"fr.esir.jxc.examples.query","fr.esir.jxc.examples.common"})
public class QueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(QueryApplication.class, args);
  }

}
