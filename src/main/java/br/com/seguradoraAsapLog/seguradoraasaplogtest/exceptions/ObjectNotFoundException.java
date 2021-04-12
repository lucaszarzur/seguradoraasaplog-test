package br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.enums.ErrorOriginEnum;

public class ObjectNotFoundException extends RuntimeException {

    private ErrorOriginEnum errorOriginEnum;

    public ObjectNotFoundException(String message, ErrorOriginEnum errorOrigin) {
        super(message);
        this.errorOriginEnum = errorOrigin;
    }

    public ErrorOriginEnum getErrorOriginEnum() {
        return errorOriginEnum;
    }
}
