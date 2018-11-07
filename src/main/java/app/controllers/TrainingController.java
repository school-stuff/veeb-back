package app.controllers;


import app.controllers.forms.TrainingForm;
import app.models.Training;
import app.repositories.TrainingRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/training")
public class TrainingController {

    private final TrainingRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository, UserRepository userRepository) {
        this.repository = trainingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Training> list() {
        List<Training> trainings = (List<Training>) repository.findAll();
        /*
        Some data transformation is needed.
        Does Spring have serializers orsth like Django?
         */
        return trainings;
    }

    @PostMapping
    public Training create(@RequestBody TrainingForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Training training = new Training();
        training.setName(form.name);
        training.setDescription(form.description);
        training.setWeeks(form.weeks);
        training.setTimesPerWeek(form.timesPerWeek);
        training.setOwner(userRepository.findByEmail(currentUserEmail));
        return repository.save(training);
    }

    @GetMapping(value = "{id}")
    public Training get(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
