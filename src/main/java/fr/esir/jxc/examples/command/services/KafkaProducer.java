package fr.esir.jxc.examples.command.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import fr.esir.jxc.examples.common.events.Event;
import fr.esir.jxc.examples.common.config.KafkaTopicConfig;
import org.springframework.util.concurrent.SettableListenableFuture;

@Service
public class KafkaProducer {

  private final KafkaTopicConfig kafkaTopicConfig;
  private final KafkaTemplate<String, Event> kafkaTemplate;

  public KafkaProducer(
    @Autowired KafkaTemplate<String, Event> kafkaTemplate,
    @Autowired KafkaTopicConfig kafkaTopicConfig
  ) {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaTopicConfig = kafkaTopicConfig;
  }

  public ListenableFuture<SendResult<String, Event>> produce(Event event) {
    return this.kafkaTemplate.send(this.kafkaTopicConfig.TOPIC, event);
  }

  public ListenableFuture<SendResult<String, Event>> produceObject(Object event) {
    return Event.of(event)
      .map(this::produce)
      .orElseGet(() -> {
        SettableListenableFuture<SendResult<String, Event>> error = new SettableListenableFuture<>();
        error.setException(new RuntimeException());
        error.cancel(true);
        return error;
      });
  }

}
