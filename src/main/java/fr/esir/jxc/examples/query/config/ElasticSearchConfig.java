package fr.esir.jxc.examples.query.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "fr.esir.jxc.examples.query.services")
@ComponentScan(basePackages = { "fr.esir.jxc.examples.query.services" })
public class ElasticSearchConfig extends fr.esir.jxc.examples.common.config.ElasticSearchConfig {


}
