package br.com.seguradoraAsapLog.seguradoraasaplogtest.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {


    public void createSessionErrorMessage(HttpServletRequest request, String attribute, String errorMessage) {
        request.getSession().setAttribute(attribute, errorMessage);
    }

    public void removeSessionAttribute(HttpSession httpSession, String attribute) {
        httpSession.removeAttribute(attribute);
    }

    public boolean hasErrorSessionByAttribute(HttpServletRequest request, String attribute) {

        return request.getSession().getAttribute(attribute) != null;
    }

    public String getErrorSession(HttpServletRequest request, String attribute) {

        return request.getSession().getAttribute(attribute) != null ? request.getSession().getAttribute(attribute).toString() : "";
    }
}
