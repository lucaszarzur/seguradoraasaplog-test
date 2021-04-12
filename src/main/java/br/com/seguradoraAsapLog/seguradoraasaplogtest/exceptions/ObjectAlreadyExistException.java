package br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.enums.ErrorOriginEnum;

public class ObjectAlreadyExistException extends RuntimeException {

    private ErrorOriginEnum errorOriginEnum;

    public ObjectAlreadyExistException(String errorMessage, ErrorOriginEnum errorOrigin) {
        super(errorMessage);
        this.errorOriginEnum = errorOrigin;
    }

    public ErrorOriginEnum getErrorOriginEnum() {
        return errorOriginEnum;
    }
}
