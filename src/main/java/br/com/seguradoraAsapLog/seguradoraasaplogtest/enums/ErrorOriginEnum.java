package br.com.seguradoraAsapLog.seguradoraasaplogtest.enums;

public enum ErrorOriginEnum {

    ERROR_CUSTOMER_GET("errorCustomerGet"),
    ERROR_CUSTOMER_CREATE("errorCustomerCreate"),
    ERROR_CUSTOMER_UPDATE("errorCustomerUpdate"),
    ERROR_CUSTOMER_DELETE("errorCustomerDelete");

    private final String errorMessage;

    ErrorOriginEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}

