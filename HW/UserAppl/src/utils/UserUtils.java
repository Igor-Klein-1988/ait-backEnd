package utils;

import model.User;
import repositories.CrudRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface UserUtils {
    default boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    default boolean hasUserWithSameEmail(User user, CrudRepository repository) {
        Predicate<User> emailMatcher = u -> u.getEmail().equals(user.getEmail());
        return findUsersMatching(repository, emailMatcher).size() > 0;
    }

    default boolean hasUserWithSameId(User user, CrudRepository repository) {
        Predicate<User> idMatcher = u -> u.getId().equals(user.getId());
        return findUsersMatching(repository, idMatcher).size() > 0;
    }

    default List<User> findUsersMatching(CrudRepository repository, Predicate<User> matcher) {
        List<User> users = repository.findAll();
        return users.stream()
                .filter(matcher)
                .collect(Collectors.toList());
    }
}
