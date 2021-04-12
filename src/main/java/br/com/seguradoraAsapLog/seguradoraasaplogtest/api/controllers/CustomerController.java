package br.com.seguradoraAsapLog.seguradoraasaplogtest.api.controllers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerModel> create(@RequestBody CustomerModel customer, HttpServletRequest request) {

        // Verifying if the request is from website
        request.getSession().setAttribute("isFromWebsite", request.getHeader("referer") != null);

        CustomerModel customerCreated = customerService.create(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customerCreated.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerModel> getCustomer(@RequestParam String name) {
        CustomerModel customer = customerService.getByName(name, "get");

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/getAllCustomers")
    public List<CustomerModel> getAllCustomers() {
        return customerService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerModel> update(@RequestBody CustomerModel customer, HttpServletRequest request) {

        // Verifying if the request is from website
        request.getSession().setAttribute("isFromWebsite", request.getHeader("referer") != null);

        CustomerModel customerUpdated = customerService.update(customer);

        return ResponseEntity.ok(customerUpdated);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String cpf) {

        customerService.delete(cpf);

        return ResponseEntity.ok("Customer with cpf: " + cpf + " deleted!");
    }

    @DeleteMapping("/deleteAllCustomers")
    public String deleteAllCustomers() {
        customerService.deleteAll();

        return "All customers deleted!";
    }
}
