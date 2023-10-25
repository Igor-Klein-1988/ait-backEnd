package repositories;

import model.User;
import utils.UserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryListImpl implements CrudRepository, UserUtils {
    private Long currentID = 1L;
    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
        user.setId(currentID++);
    }

    @Override
    public User findByID(Long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public List<User> findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public void update(User user) {
    }
}
