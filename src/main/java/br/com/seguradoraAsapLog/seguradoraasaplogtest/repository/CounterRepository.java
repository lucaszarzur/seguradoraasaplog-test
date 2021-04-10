package br.com.seguradoraAsapLog.seguradoraasaplogtest.repository;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {

}
