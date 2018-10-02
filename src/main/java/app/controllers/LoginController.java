package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void handleLogin(String email, String password, HttpServletRequest request) throws ServletException {
        request.login(email, password);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logOut(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
