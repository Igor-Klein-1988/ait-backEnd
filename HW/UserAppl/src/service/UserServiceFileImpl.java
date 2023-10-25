package service;

import model.User;
import repositories.CrudRepository;
import repositories.UserRepositoryFileImpl;
import utils.UserUtils;

import java.util.List;

public class UserServiceFileImpl implements UserService, UserUtils {
    CrudRepository repository = new UserRepositoryFileImpl("users.txt");

    public UserServiceFileImpl(String fileName) {
        this.repository = new UserRepositoryFileImpl(fileName);
    }

    public UserServiceFileImpl() {
        this.repository = new UserRepositoryFileImpl("users.txt");
    }

    @Override
    public boolean addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(NULL_USER_ERROR_MESSAGE);
        }

        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException(INVALID_EMAIL_ERROR_MESSAGE);
        }

        if (hasUserWithSameEmail(user, repository)) {
            throw new IllegalArgumentException(DUPLICATE_EMAIL_ERROR_MESSAGE);
        }

        repository.save(user);
        return true;
    }


    @Override
    public List<User> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
