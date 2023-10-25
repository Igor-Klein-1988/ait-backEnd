package repositories;

import model.User;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryFileImpl implements CrudRepository {
    private String fileName;
    private Long currentID = 1L;

    public UserRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        user.setId(currentID++);
        writeUsersToFile(Collections.singletonList(user), true, "File save error");
    }


    private void saveAllUsers(List<User> users) {
        writeUsersToFile(users, false, "Error saving users");
    }

    private void writeUsersToFile(List<User> users, boolean append, String massage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            for (User user : users) {
                writer.write(user.getId() + ";" + user.getName() + ";" + user.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(massage, e);
        }
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(l -> l.split(";"))
                    .map(uArr -> new User(Long.parseLong(uArr[0]), uArr[1], uArr[2]))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findByName(String name) {
        List<User> users = findAll();

        return users.stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        List<User> users = findAll();
        users.removeIf(user -> user.getId().equals(id));
        saveAllUsers(users);
    }

    @Override
    public void update(User user) {
    }
}
