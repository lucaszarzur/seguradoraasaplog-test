package br.com.seguradoraAsapLog.seguradoraasaplogtest.repository;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel, String> {

    public Optional<CustomerModel> findByName(String name);

    public Optional<CustomerModel> findByCpf(String cpf);
}
