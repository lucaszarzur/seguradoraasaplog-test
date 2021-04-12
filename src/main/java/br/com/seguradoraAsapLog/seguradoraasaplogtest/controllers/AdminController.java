package br.com.seguradoraAsapLog.seguradoraasaplogtest.controllers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectNotFoundException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.CustomerService;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.InsurancePolicyService;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CustomerService customerService;

    @Autowired
    InsurancePolicyService insurancePolicyService;

    @Autowired
    private SessionUtils sessionUtils;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomersPage(ModelAndView modelAndView, HttpServletRequest request, HttpSession httpSession) {

        List<CustomerModel> customersList = customerService.getAll();
        List<String> insurancePoliciesList = insurancePolicyService.getAll().stream().filter(Objects::nonNull).map(i -> i.getId()).collect(Collectors.toList());

        modelAndView.setViewName("customer/customers");
        modelAndView.addObject("customers", customersList);
        modelAndView.addObject("insurancePolicies", insurancePoliciesList);

        if (sessionUtils.hasErrorSessionByAttribute(request, "errorMessageCustomerCreateOrUpdate")) {
            String error = sessionUtils.getErrorSession(request, "errorMessageCustomerCreateOrUpdate");
            modelAndView.addObject("errorMessageCustomerCreateOrUpdate", error);

            sessionUtils.removeSessionAttribute(httpSession, "errorMessageCustomerCreateOrUpdate");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/insurance-policies", method = RequestMethod.GET)
    public ModelAndView getInsurancePolicies(ModelAndView modelAndView, HttpServletRequest request) {

        List<InsurancePolicyModel> insurancePoliciesList = insurancePolicyService.getAll();


        modelAndView.setViewName("insurancepolicy/insurancepolicy");
        modelAndView.addObject("insurancePolicies", insurancePoliciesList);

        return modelAndView;
    }

    @GetMapping("/customer/delete")
    public String deleteCustomer(@RequestParam String cpf) {
        CustomerModel customerDeleted = customerService.findByCpf(cpf, "delete");

        if (customerDeleted != null) {
            customerService.delete(cpf);

            return "redirect:/admin/customers";
        }

        return "";
    }
}
