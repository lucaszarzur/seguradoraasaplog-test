package br.com.seguradoraAsapLog.seguradoraasaplogtest.service;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.enums.ErrorOriginEnum;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectAlreadyExistException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectNotFoundException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.repository.CustomerRepository;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private ExceptionUtils exceptionUtils;

    public CustomerModel findByCpf(String cpf, String origin) {
        return customerRepository.findByCpf(cpf).stream().findFirst().orElseThrow(() ->
                new ObjectNotFoundException("Object not found! CPF: " + cpf, exceptionUtils.getErrorOriginEnumByOrigin(origin)));
    }

    public List<CustomerModel> getAll() {
        return customerRepository.findAll();
    }

    public CustomerModel getById(String id, String origin) {
        return customerRepository.findById(id).stream().findFirst().orElseThrow(() ->
                new ObjectNotFoundException("Object not found! ID: " + id, exceptionUtils.getErrorOriginEnumByOrigin(origin)));
    }

    public CustomerModel getByName(String name, String origin) {
        return customerRepository.findByName(name).stream().findFirst().orElseThrow(() ->
                new ObjectNotFoundException("Object not found! Name: " + name, exceptionUtils.getErrorOriginEnumByOrigin(origin)));
    }

    public CustomerModel create(CustomerModel customer) {

        boolean customerAlreadyExist = getAll().stream().anyMatch(c -> c.getCpf().equals(customer.getCpf()));
        InsurancePolicyModel insurancePolicyToCreate = insurancePolicyService.getById(customer.getInsurancePolicies().iterator().next().getId().replace("[", "").replace("]", ""), "create");

        ArrayList<InsurancePolicyModel> insurancePolicyList = new ArrayList<>();
        if (customerAlreadyExist) {
            throw new ObjectAlreadyExistException("Error in create customer, because the customer with this CPF already exist! Customer CPF: " + customer.getCpf(), ErrorOriginEnum.ERROR_CUSTOMER_CREATE);
        }

        if (insurancePolicyToCreate != null) {
            for (InsurancePolicyModel id : customer.getInsurancePolicies()) {
                insurancePolicyList.add(insurancePolicyService.getByInsurancePolicyNumber(id.getInsurancePolicyNumber()));
            }

            return customerRepository.save(new CustomerModel(customer.getName(), customer.getPassword(), customer.getCpf(), customer.getCity(), customer.getState(), insurancePolicyList));
        } else {
            throw new ObjectNotFoundException("Error in update customer, because the insurance policy doesn't exist! Customer CPF: " + customer.getCpf(), ErrorOriginEnum.ERROR_CUSTOMER_CREATE);
        }
    }

    public CustomerModel update(CustomerModel customer) {
        CustomerModel customerOnDB = getById(customer.getId(), "update");

        InsurancePolicyModel insurancePolicyToEdit = insurancePolicyService.getById(customer.getInsurancePolicies().iterator().next().getId().replace("[", "").replace("]", ""), "update");

        if (insurancePolicyToEdit != null) {

            customerOnDB.setName(customer.getName());
            customerOnDB.setCpf(customer.getCpf());
            customerOnDB.setCity(customer.getCity());
            customerOnDB.setState(customer.getState());
            customerOnDB.setInsurancePolicies(Arrays.asList(insurancePolicyToEdit));

            return customerRepository.save(customerOnDB);
        } else {
            throw new ObjectNotFoundException("Error in update customer, because the insurance policy doesn't exist! Customer CPF: " + customer.getCpf(), ErrorOriginEnum.ERROR_CUSTOMER_UPDATE);
        }
    }

    public void delete(String cpf) {
        CustomerModel customer = findByCpf(cpf, "delete");

        customerRepository.delete(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

}
