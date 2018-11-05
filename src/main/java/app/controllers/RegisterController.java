package app.controllers;

import app.models.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@RestController
@RequestMapping
public class RegisterController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterForm registerForm, HttpServletRequest request) throws ServletException {
        String email = registerForm.email;
        String password = registerForm.password;

        if (userRepository.findByEmail(registerForm.email) != null) {
            return ResponseEntity.status(400).body("A user with the given email already exists.");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok().body(registerForm);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody UserForm userForm, HttpServletRequest request) {
        String dateOfBirth = userForm.dateOfBirth;
        String firstName = userForm.firstName;
        String lastName = userForm.lastName;
        boolean trainer = userForm.trainer;

        User user = userRepository.findById(id);
        if (user == null) {
            return ResponseEntity.status(404).body(String.format("User of id %s does not exist.", id));
        }
        user.setDayOfBirth(LocalDate.parse(dateOfBirth));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTrainer(trainer);
        userRepository.persist(user);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
    }
}
