import model.User;
import service.UserService;
import service.UserServiceFileImpl;
import service.UserServiceListImpl;
import utils.UserUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRegistration implements UserUtils {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        //repositories.CrudRepository repository = new repositories.UserRepositoryListImpl();
//        CrudRepository repository = new UserRepositoryFileImpl("users.txt");
//        CrudRepository repository = new UserRepositoryListImpl();
        UserService userService = new UserServiceFileImpl();
//        UserService userService = new UserServiceListImpl();
        while (true) {
            System.out.print("Enter name (or 'exit' to quit): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            // Email validation
            String email = null;
            while (true) {
                System.out.print("Enter email: ");
                email = scanner.nextLine();

                // через анонимный класс использую
                UserUtils userUtils = new UserUtils() {
                };
                boolean isValid = userUtils.isValidEmail(email);

                if (isValid) {
                    break;
                } else {
                    System.out.println("Email must contain the '@' symbol. Please enter a valid email.");
                }
            }

            User user = new User(name, email);
            userService.addUser(user);
        }
        //saveUsersToFile(users, "src/users.txt");

        System.out.println(userService.findAll());
    }

    private static void saveUsersToFile(List<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getName() + " " + user.getEmail());
                writer.newLine(); // Move to a new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
