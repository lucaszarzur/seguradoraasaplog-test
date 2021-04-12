package br.com.seguradoraAsapLog.seguradoraasaplogtest.handlers;

import br.com.seguradoraAsapLog.seguradoraasaplogtest.enums.ErrorOriginEnum;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectAlreadyExistException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.exceptions.ObjectNotFoundException;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.model.response.StandardErrorResponse;
import br.com.seguradoraAsapLog.seguradoraasaplogtest.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@ControllerAdvice
public class ResourceExceptionHandler {

    @Autowired
    private SessionUtils sessionUtils;

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        // if the error is from website, handle with different way
        if (request.getSession().getAttribute("isFromWebsite") != null) {
            handleErrorWebsite(request, e.getErrorOriginEnum());
        }

        StandardErrorResponse error = new StandardErrorResponse(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<StandardErrorResponse> objectAlreadyExist(ObjectAlreadyExistException e, HttpServletRequest request) {
        // if the error is from website, handle with different way
        if (request.getSession().getAttribute("isFromWebsite") != null) {
            handleErrorWebsite(request, e.getErrorOriginEnum());
        }

        StandardErrorResponse error = new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ModelAndView handleErrorWebsite(HttpServletRequest request, ErrorOriginEnum errorOrigin) {
        ModelAndView modelAndView = new ModelAndView();

        // Load properties file from class path
        ResourceBundle rb = ResourceBundle.getBundle("errors", new Locale("pt"));
        String propertyValue = rb.getString(errorOrigin.toString());
        sessionUtils.createSessionErrorMessage(request, "errorMessageCustomerCreateOrUpdate", propertyValue);

        return modelAndView;
    }
}
