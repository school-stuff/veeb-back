package app.repositories;

import app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom {
    User findByEmail(String email);

    User findById(int id);
}
