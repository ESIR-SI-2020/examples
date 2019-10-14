package fr.esir.jxc.examples.user_events_handler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "fr.esir.jxc.examples.user_events_handler.services")
@ComponentScan(basePackages = { "fr.esir.jxc.examples.user_events_handler.services" })
public class ElasticSearchConfig extends fr.esir.jxc.examples.common.config.ElasticSearchConfig {


}
