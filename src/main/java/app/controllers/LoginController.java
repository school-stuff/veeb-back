package app.controllers;

import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoginController {
    private final UserRepository users;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.users = userRepository;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity handleLogin(String email, String password, HttpServletRequest request) throws ServletException {
        request.login(email, password);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(users.findByEmail(email));
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logOut(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
