package br.com.seguradoraAsapLog.seguradoraasaplogtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {


/*    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView showLogin() {

        ModelAndView modelAndView = new ModelAndView("login/login");
        modelAndView.addObject("customer", new CustomerModel());

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("user") UserModel user, ModelAndView modelAndView) {

        if (userService.doLogin(user.getEmail(), user.getPassword()) != null) {
            LOG.info("User " + user.getEmail() + " exists, redirecting to homepage...");

            UserModel userModel = userService.findByEmail(user.getEmail());

            // Without Spring Security, this is the best way I found to deal with current user
            session.setCurrentUser(userModel);

            modelAndView.addObject("userName", userModel.getName());
            modelAndView.addObject("email", userModel.getEmail());

            return REDIRECT_TO_HOMEPAGE;
        } else {
            LOG.info("User " + user.getEmail() + "doesn't exists, redirecting to login page...");

            return REDIRECT_TO_LOGIN;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        return REDIRECT_TO_LOGIN;
    }*/
}
