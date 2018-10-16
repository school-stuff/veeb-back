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

    private final UserRepository userRepo;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseEntity saveUser(
            @RequestBody RegisterForm registerForm,
            HttpServletRequest request
    ) throws ServletException {
        String email = registerForm.email;
        String password = registerForm.password;

        if (userRepo.findByEmail(registerForm.email) != null) {
            return ResponseEntity.status(400).body("A user with the given email already exists.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        System.out.println(user);
        userRepo.save(user);
        request.login(email, password);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(registerForm);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public ResponseEntity updateUser(
            @PathVariable int id,
            @RequestBody UserForm userForm,
            HttpServletRequest request
    ) {
        String dateOfBirth = userForm.dateOfBirth;
        String firstName = userForm.firstName;
        String lastName = userForm.lastName;
        boolean trainer = userForm.trainer;

        User user = userRepo.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body("User of id %s does not exist." + id);
        }
        user.setDayOfBirth(LocalDate.parse(dateOfBirth));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTrainer(trainer);
        userRepo.persist(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
