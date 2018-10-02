package app.repositories;

import app.models.User;

public interface UserRepositoryCustom {
    void persist(User user);
}
