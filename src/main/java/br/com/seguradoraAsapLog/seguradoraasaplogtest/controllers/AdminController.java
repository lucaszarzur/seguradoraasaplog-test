package br.com.seguradoraAsapLog.seguradoraasaplogtest.controllers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.CustomerModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.InsurancePolicyModel;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.CustomerService;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CustomerService customerService;

    @Autowired
    InsurancePolicyService insurancePolicyService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomersPage(ModelAndView modelAndView, HttpServletRequest request) {

        List<CustomerModel> customersList = customerService.getAll();
        modelAndView.setViewName("customer/customers");
        modelAndView.addObject("customers", customersList);

        return modelAndView;
    }

    @RequestMapping(value = "/insurance-policies", method = RequestMethod.GET)
    public ModelAndView getInsurancePolicies(ModelAndView modelAndView, HttpServletRequest request) {

        List<InsurancePolicyModel> insurancePoliciesList = insurancePolicyService.getAll();


        modelAndView.setViewName("insurancepolicy/insurancepolicy");
        modelAndView.addObject("insurancePolicies", insurancePoliciesList);

        return modelAndView;
    }
}
