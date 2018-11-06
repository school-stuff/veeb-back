package app.controllers;


import app.models.Training;
import app.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/training")
public class TrainingController {

    TrainingRepository repository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.repository = trainingRepository;
    }

    @GetMapping("/training")
    List<Training> list() {
        System.out.println("Get all trainings.");
        return (List<Training>) repository.findAll();
    }

    @PostMapping("/training")
    Training create(@RequestBody Training training) {
        System.out.println("Create a training: ");
        System.out.println(training);
        return repository.save(training);
    }

    @GetMapping(value = "/training/{id}")
    public Training get(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/training/{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
