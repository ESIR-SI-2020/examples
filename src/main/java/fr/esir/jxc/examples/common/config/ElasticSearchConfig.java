package fr.esir.jxc.examples.common.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Log4j2
public class ElasticSearchConfig {

  @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch/5.6.0}")
  private String elasticsearchHome;

  @Value("${elasticsearch.cluster.name:elasticsearch}")
  private String clusterName;

  @Value("${elasticsearch.cluster.host:127.0.0.1}")
  private String clusterHost;

  @Value("${elasticsearch.cluster.port:9300}")
  private int clusterPort;

  @Bean
  public Client client() {
    Settings settings = Settings.builder()
      .put("path.home", elasticsearchHome)
      .put("cluster.name", clusterName).build();

    try {
      TransportClient client = new PreBuiltTransportClient(settings);
      client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(clusterHost), clusterPort));
      return client;
    } catch (UnknownHostException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {
    return new ElasticsearchTemplate(client());
  }

}
