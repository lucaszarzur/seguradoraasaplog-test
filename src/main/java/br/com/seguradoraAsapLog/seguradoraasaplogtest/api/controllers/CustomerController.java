package br.com.seguradoraAsapLog.seguradoraasaplogtest.api.controllers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.CustomerService;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerModel> create(@RequestBody CustomerModel customer) {

        CustomerModel customerCreated = new CustomerModel();
        boolean customerExists = !(customerService.getByName(customer.getName()) == null);
        if (!customerExists) {

            if (!customerService.isCpfAlreadyExists(customer.getCpf())) {

                customerCreated = customerService.create(customer.getName(), customer.getPassword(), customer.getCpf(), customer.getCity(),
                        customer.getState(), customer.getInsurancePolicies());
                return ResponseEntity.ok(customerCreated);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerModel> getCustomer(@RequestParam String name) {
        CustomerModel customer = customerService.getByName(name);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAllCustomers")
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerModel> update(@RequestBody CustomerModel customer) {

        CustomerModel customerUpdated = customerService.getByName(customer.getName());

        if (customerUpdated != null) {

            customerUpdated = customerService.update(customer.getName(), customer.getPassword(), customer.getCpf(),
                    customer.getCity(), customer.getState(), customer.getInsurancePolicies());

            return ResponseEntity.ok(customerUpdated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String name) {
        CustomerModel customerDeleted = customerService.getByName(name);

        if (customerDeleted != null) {
            customerService.delete(name);

            return ResponseEntity.ok("Customer with name: " + name + " deleted!");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAllCustomers")
    public String deleteAllCustomers() {
        customerService.deleteAll();

        return "All customers deleted!";
    }
}
