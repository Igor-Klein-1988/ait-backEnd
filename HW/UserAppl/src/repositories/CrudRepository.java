package repositories;

import model.User;

import java.util.List;

public interface CrudRepository {
    void save(User user);

    User findByID(Long id);

    List<User> findAll();

    List<User> findByName(String name);

    void deleteById(Long id);

    void update(User user);

}
