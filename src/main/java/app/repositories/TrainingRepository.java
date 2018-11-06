package app.repositories;

import app.models.Training;
import org.springframework.data.repository.CrudRepository;


public interface TrainingRepository extends CrudRepository<Training, Integer> {

}