package app.controllers;

import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping
public class LoginController {
    private final UserRepository users;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.users = userRepository;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity handleLogin(
            @RequestBody RegisterForm registerForm,
            HttpServletRequest request) throws ServletException {
        String email = registerForm.email;
        String password = registerForm.password;
        request.login(email, password);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(users.findByEmail(email));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/logout")
    public void logOut(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
