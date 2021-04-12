package br.com.seguradoraAsapLog.seguradoraasaplogtest.util;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.enums.ErrorOriginEnum;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtils {

    public ErrorOriginEnum getErrorOriginEnumByOrigin(String origin) {

        switch (origin.toUpperCase()) {
            case "GET":
                return ErrorOriginEnum.ERROR_CUSTOMER_GET;

            case "CREATE":
                return ErrorOriginEnum.ERROR_CUSTOMER_CREATE;

            case "UPDATE":
                return ErrorOriginEnum.ERROR_CUSTOMER_UPDATE;

            case "DELETE":
                return ErrorOriginEnum.ERROR_CUSTOMER_DELETE;
            default:
                return null;
        }
    }
}
