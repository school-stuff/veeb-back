package app.repositories;

import app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {
    User findByEmail(String email);
}
