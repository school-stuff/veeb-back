package app.controllers;

import app.controllers.forms.RegisterForm;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping
public class LoginController {
    private final UserRepository users;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.users = userRepository;
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public void handleLogin(
            @RequestBody RegisterForm registerForm,
            HttpServletRequest request) throws ServletException {
        String email = registerForm.email;
        String password = registerForm.password;
        request.login(email, password);

        System.out.println(email + ' ' + password);
        System.out.println(789);
    }

    @PostMapping(value = "/logout")
    public void logOut(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
