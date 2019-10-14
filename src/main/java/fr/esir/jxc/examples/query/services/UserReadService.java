package fr.esir.jxc.examples.query.services;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import fr.esir.jxc.examples.common.models.User;

@Service
public interface UserReadService extends ElasticsearchRepository<User, String> {

}
