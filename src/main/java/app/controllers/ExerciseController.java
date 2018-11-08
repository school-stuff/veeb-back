package app.controllers;


import app.controllers.forms.ExerciseForm;
import app.models.Exercise;
import app.repositories.ExerciseRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/exercise")
public class ExerciseController {

    private final ExerciseRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseController(ExerciseRepository exerciseRepository, UserRepository userRepository) {
        this.repository = exerciseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Exercise> list() {
        List<Exercise> exercises = (List<Exercise>) repository.findAll();
        /*
        Some data transformation is needed.
        Does Spring have serializers orsth like Django?
         */
        return exercises;
    }

    @PostMapping
    public Exercise create(@RequestBody ExerciseForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Exercise exercise = new Exercise();
        exercise.setName(form.name);
        exercise.setDescription(form.description);
        exercise.setAmount(form.amount);
        exercise.setOwner(userRepository.findByEmail(currentUserEmail));
        return repository.save(exercise);
    }

    @GetMapping(value = "{id}")
    public Exercise get(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
