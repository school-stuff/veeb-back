package app.controllers;

import app.repositories.UserRepository;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@Controller
@RequestMapping
public class RegisterController {

    private final UserRepository users;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.users = userRepository;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public void saveUser(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request
    ) throws ServletException {
        User user = new User();

        if (users.findByEmail(email) == null) {
            user.setEmail(email);
            user.setPassword(hashPassword(password));
        }
        users.save(user);
        request.login(email, password);
    }

    @PostMapping(value = "/onboarding")
    @ResponseBody
    public void addOnboardinFields(
            @RequestParam String dateOfBirth,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String trainer,
            HttpServletRequest request
    ) {
        User user = users.findByEmail(request.getRemoteUser());
        if (user != null) {
            user.setDayOfBirth(LocalDate.parse(dateOfBirth));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            if (trainer.equals("true")){
                user.setTrainer(true);
            }
            //TODO: save
        }

    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
