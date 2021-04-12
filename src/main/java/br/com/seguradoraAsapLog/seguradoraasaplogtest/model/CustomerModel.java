package br.com.seguradoraAsapLog.seguradoraasaplogtest.model;

import com.mongodb.lang.NonNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class CustomerModel {

    @Id
    String id;

    @NonNull
    String name;

    String password;

    @CPF
    @NonNull
    String cpf;

    @NonNull
    String city;

    @NonNull
    String state;

    @DBRef
    private List<InsurancePolicyModel> insurancePolicies;

    public CustomerModel(@NonNull String name, String password, @NonNull @CPF String cpf, @NonNull String city,
                         @NonNull String state, List<InsurancePolicyModel> insurancePolicies) {
        this.name = name;
        this.password = password;
        this.cpf = cpf;
        this.city = city;
        this.state = state;
        this.insurancePolicies = insurancePolicies;
    }

    public CustomerModel() {
    }

    // getters and setters

    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    public List<InsurancePolicyModel> getInsurancePolicies() {
        return insurancePolicies;
    }

    public void setInsurancePolicies(List<InsurancePolicyModel> insurancePolicies) {
        this.insurancePolicies = insurancePolicies;
    }
}
