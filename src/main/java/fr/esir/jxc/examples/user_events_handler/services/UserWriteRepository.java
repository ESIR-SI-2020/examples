package fr.esir.jxc.examples.user_events_handler.services;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import fr.esir.jxc.examples.common.models.User;

// More info here: https://www.baeldung.com/spring-data-elasticsearch-tutorial
@Repository
public interface UserWriteRepository extends ElasticsearchRepository<User, String> {

}
