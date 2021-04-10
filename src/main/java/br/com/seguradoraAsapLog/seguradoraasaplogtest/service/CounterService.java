package br.com.seguradoraAsapLog.seguradoraasaplogtest.service;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.Counter;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class CounterService {

    @Autowired
    private MongoOperations mongo;

    @Autowired
    private CounterRepository counterRepository;

    public void createSequence() {

        Counter counter = counterRepository.findById("customers").stream().findFirst().orElse(null);
        if (counter == null) {
            // Create "counters" collection
            mongo.getCollection("counters");

            // Save first sequence
            counterRepository.save(new Counter("customers", 0));
        }
    }

    public int getNextSequence(String collectionName) {

        createSequence();

        Counter counter = mongo.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().returnNew(true),
                Counter.class);

        return counter.getSeq();
    }
}