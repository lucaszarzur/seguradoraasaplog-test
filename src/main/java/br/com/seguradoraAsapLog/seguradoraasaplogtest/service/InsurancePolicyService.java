package br.com.seguradoraAsapLog.seguradoraasaplogtest.service;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectNotFoundException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.repository.InsurancePolicyRepository;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CounterService counterService;

    @Autowired
    private ExceptionUtils exceptionUtils;


    public InsurancePolicyModel create(Date startTerm, Date endTerm,
                                       String vehiclePlate, Double insurancePolicyValue, String customerName) {

        CustomerModel customer = customerService.getByName(customerName, "create");

        InsurancePolicyModel insurancePolicy = insurancePolicyRepository.save(new InsurancePolicyModel(counterService.getNextSequence("customers"),
                startTerm, endTerm, vehiclePlate, insurancePolicyValue, customerName, customer));

        return insurancePolicy;
    }

    public InsurancePolicyModel getByInsurancePolicyNumber(Integer insurancePolicyNumber) {
        return insurancePolicyRepository.getByInsurancePolicyNumber(insurancePolicyNumber);
    }

    public List<InsurancePolicyModel> getAll() {
        return insurancePolicyRepository.findAll();
    }

    public InsurancePolicyModel getById(String insurancePolicyId, String origin) {
        return insurancePolicyRepository.findById(insurancePolicyId).stream().findFirst().orElseThrow(() ->
                new ObjectNotFoundException("Object not found! ID: " + insurancePolicyId, exceptionUtils.getErrorOriginEnumByOrigin(origin)));
    }

    public InsurancePolicyModel update(Integer insurancePolicyNumber, Date startTerm, Date endTerm, String vehiclePlate,
                                       Double insurancePolicyValue) {
        InsurancePolicyModel insurancePolicy = insurancePolicyRepository.getByInsurancePolicyNumber(insurancePolicyNumber);

        insurancePolicy.setStartTerm(startTerm);
        insurancePolicy.setEndTerm(endTerm);
        insurancePolicy.setVehiclePlate(vehiclePlate);
        insurancePolicy.setInsurancePolicyValue(insurancePolicyValue);

        return insurancePolicyRepository.save(insurancePolicy);
    }

    public void delete(Integer insurancePolicyNumber) {
        InsurancePolicyModel insurancePolicy = insurancePolicyRepository.getByInsurancePolicyNumber(insurancePolicyNumber);

        insurancePolicyRepository.delete(insurancePolicy);
    }

    public void deleteAll() {
        insurancePolicyRepository.deleteAll();
    }

}
