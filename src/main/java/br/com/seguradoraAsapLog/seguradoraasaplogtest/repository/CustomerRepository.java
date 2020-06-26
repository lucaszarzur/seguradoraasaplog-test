package br.com.seguradoraAsapLog.seguradoraasaplogtest.repository;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel, String> {

    public CustomerModel findByName(String name);

    public CustomerModel findByCpf(String cpf);
}
