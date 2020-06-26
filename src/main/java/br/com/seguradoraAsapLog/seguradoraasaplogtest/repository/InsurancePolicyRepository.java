package br.com.seguradoraAsapLog.seguradoraasaplogtest.repository;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends MongoRepository<InsurancePolicyModel, String> {

    public InsurancePolicyModel getByInsurancePolicyNumber(Integer insurancePolicyNumber);
}
