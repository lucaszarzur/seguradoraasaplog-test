package br.com.seguradoraAsapLog.seguradoraasaplogtest.api.controllers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.CustomerService;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/insurance-policies")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InsurancePolicyModel> create(@RequestBody InsurancePolicyModel insurancePolicy) {

        InsurancePolicyModel insurancePolicyCreated = new InsurancePolicyModel();
        boolean insurancePolicyExists = !(insurancePolicyService.getByInsurancePolicyNumber(insurancePolicy.getInsurancePolicyNumber()) == null);
        if (!insurancePolicyExists) {

            CustomerModel customer = customerService.getByName(insurancePolicy.getCustomerName());

            if (customer != null) {

                insurancePolicyCreated = insurancePolicyService.create(
                        insurancePolicy.getStartTerm(), insurancePolicy.getEndTerm(), insurancePolicy.getVehiclePlate(),
                        insurancePolicy.getInsurancePolicyValue(), insurancePolicy.getCustomerName());

                return ResponseEntity.ok(insurancePolicyCreated);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get")
    public ResponseEntity<InsurancePolicyModel> getInsurancePolicy(@RequestParam Integer insurancePolicyNumber) {
        InsurancePolicyModel insurancePolicy = insurancePolicyService.getByInsurancePolicyNumber(insurancePolicyNumber);

        if (insurancePolicy != null) {
            return ResponseEntity.ok(insurancePolicy);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAllInsurancePolicies")
    public List<InsurancePolicyModel> getAllInsurancePolicies() {
        return insurancePolicyService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<InsurancePolicyModel> update(@RequestBody InsurancePolicyModel insurancePolicy) {
        InsurancePolicyModel insurancePolicyUpdated = insurancePolicyService.getByInsurancePolicyNumber(insurancePolicy.getInsurancePolicyNumber());

        if (insurancePolicyUpdated != null) {
            // insurancePolicyUpdated can't be update, because of this, I get the insurancePolicyUpdated property
            insurancePolicyUpdated= insurancePolicyService.update(insurancePolicyUpdated.getInsurancePolicyNumber(), insurancePolicy.getStartTerm(),
                    insurancePolicy.getEndTerm(), insurancePolicy.getVehiclePlate(), insurancePolicy.getInsurancePolicyValue());

            return ResponseEntity.ok(insurancePolicyUpdated);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Integer insurancePolicyNumber) {
        InsurancePolicyModel insurancePolicyDeleted = insurancePolicyService.getByInsurancePolicyNumber(insurancePolicyNumber);

        if (insurancePolicyDeleted != null) {
            insurancePolicyService.delete(insurancePolicyNumber);

            return ResponseEntity.ok("Insurance policy with number: " + insurancePolicyNumber + " deleted!");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAllInsurancePolicies")
    public String deleteAllInsurancePolicies() {
        insurancePolicyService.deleteAll();

        return "All insurance policies deleted!";
    }
}
