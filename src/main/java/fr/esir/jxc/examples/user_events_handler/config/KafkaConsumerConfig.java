package fr.esir.jxc.examples.user_events_handler.config;

import java.util.HashMap;
import java.util.Map;

import fr.esir.jxc.examples.common.config.KafkaTopicConfig;
import fr.esir.jxc.examples.common.events.Event;
import fr.esir.jxc.examples.user_events_handler.services.UserEventsListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  private final KafkaTopicConfig kafkaTopicConfig;

  public KafkaConsumerConfig(@Autowired KafkaTopicConfig kafkaTopicConfig) {
    this.kafkaTopicConfig = kafkaTopicConfig;
  }

  @Bean
  public KafkaMessageListenerContainer<String, Event> messageListenerContainer(
    @Value("${kafka.topic}") String topic,
    @Autowired UserEventsListener eventsListener
  ) {

    ContainerProperties containerProperties = new ContainerProperties(topic);
    containerProperties.setMessageListener(eventsListener);

    KafkaMessageListenerContainer<String, Event> listenerContainer =
      new KafkaMessageListenerContainer<>(consumerFactory(), containerProperties);

    listenerContainer.setAutoStartup(true);

    // bean name is the prefix of kafka consumer thread name
    listenerContainer.setBeanName("kafka-user-message-listener");
    return listenerContainer;
  }

  @Bean
  public ConsumerFactory<String, Event> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
      consumerProperties(),
      new StringDeserializer(),
      new JsonDeserializer<>(Event.class)
    );
  }

  public Map<String, Object> consumerProperties() {
    Map<String, Object> props = new HashMap<>();

    props.put(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
      this.kafkaTopicConfig.BOOTSTRAP_ADDRESS);

    props.put(
      ConsumerConfig.GROUP_ID_CONFIG,
      this.kafkaTopicConfig.GROUP_ID);

    props.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      StringDeserializer.class);

    props.put(
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
      JsonSerializer.class);

    return props;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Event> factory =
      new ConcurrentKafkaListenerContainerFactory<>();

    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}
