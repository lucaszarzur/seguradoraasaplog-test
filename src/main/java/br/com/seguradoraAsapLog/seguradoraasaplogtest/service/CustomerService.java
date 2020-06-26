package br.com.seguradoraAsapLog.seguradoraasaplogtest.service;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    public CustomerModel create(String name, String password, String cpf, String city, String state, List<InsurancePolicyModel> insurancePolicies) {

        ArrayList<InsurancePolicyModel> insurancePolicyList = new ArrayList<>();
        for (InsurancePolicyModel id : insurancePolicies) {
            insurancePolicyList.add(insurancePolicyService.getByInsurancePolicyNumber(id.getInsurancePolicyNumber()));
        }

        return customerRepository.save(new CustomerModel(name, password, cpf, city, state, insurancePolicyList));
    }

    public List<CustomerModel> getAll() {
        return customerRepository.findAll();
    }

    public CustomerModel getByName(String name) {
        return customerRepository.findByName(name);
    }

    public CustomerModel update(String name, String password, String cpf, String city, String state,
                                List<InsurancePolicyModel> insurancePolicies) {
        CustomerModel customer = customerRepository.findByName(name);

        customer.setPassword(password);
        customer.setCpf(cpf);
        customer.setCity(city);
        customer.setState(state);
        customer.setInsurancePolicies(insurancePolicies);

        return customerRepository.save(customer);
    }

    public void delete(String name) {
        CustomerModel customer = customerRepository.findByName(name);

        customerRepository.delete(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public CustomerModel findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    public Boolean isCpfAlreadyExists(String cpf) {

        CustomerModel customerCpf = findByCpf(cpf);
        if (customerCpf != null) {
            return customerCpf.getCpf().equals(cpf);
        } else {
            return false;
        }
    }

}
