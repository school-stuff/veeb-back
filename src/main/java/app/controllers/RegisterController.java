package app.controllers;

import app.repositories.UserRepository;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity saveUser(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request
    ) throws ServletException {

        if (users.findByEmail(email) == null) {
            return ResponseEntity.status(400).body("");
            // TODO: correct error
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        users.save(user);
        request.login(email, password);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
    }

    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity updateUser(
            @PathVariable int id,
            @RequestParam String dateOfBirth,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String trainer,
            HttpServletRequest request
    ) {
        User user = users.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body("");
            // TODO: correct error
        }
        user.setDayOfBirth(LocalDate.parse(dateOfBirth));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if (trainer.equals("true")){
            user.setTrainer(true);
        } else if (trainer.equals("false")){
            user.setTrainer(false);
        }
        users.persist(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
