package br.com.seguradoraAsapLog.seguradoraasaplogtest.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class InsurancePolicyModel {

    @Id
    String id;

    @NonNull
    Integer insurancePolicyNumber;

    @NonNull
    Date startTerm;

    @NonNull
    Date endTerm;

    @NonNull
    String vehiclePlate;

    @NonNull
    Double insurancePolicyValue;

    @NonNull
    String customerName;

    @DBRef
    private CustomerModel customer;

    public InsurancePolicyModel(@NonNull int insurancePolicyNumber, @NonNull Date startTerm, @NonNull Date endTerm,
                                @NonNull String vehiclePlate, @NonNull Double insurancePolicyValue, @NonNull String customerName, CustomerModel customer) {
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.startTerm = startTerm;
        this.endTerm = endTerm;
        this.vehiclePlate = vehiclePlate;
        this.insurancePolicyValue = insurancePolicyValue;
        this.customerName = customerName;
        this.customer = customer;
    }

    public InsurancePolicyModel() {
    }

    // getters and setters


    public String getId() {
        return id;
    }

    @NonNull
    public Integer getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(@NonNull Integer insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @NonNull
    public Date getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(@NonNull Date startTerm) {
        this.startTerm = startTerm;
    }

    @NonNull
    public Date getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(@NonNull Date endTerm) {
        this.endTerm = endTerm;
    }

    @NonNull
    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(@NonNull String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    @NonNull
    public Double getInsurancePolicyValue() {
        return insurancePolicyValue;
    }

    public void setInsurancePolicyValue(@NonNull Double insurancePolicyValue) {
        this.insurancePolicyValue = insurancePolicyValue;
    }

    @NonNull
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NonNull String customerName) {
        this.customerName = customerName;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
