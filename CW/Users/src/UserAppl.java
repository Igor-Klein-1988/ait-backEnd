import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAppl {
    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        Scanner scanner = new Scanner(System.in);

        boolean isFinished = false;

        while (!isFinished) {
            System.out.println("Enter the name");
            String name = scanner.nextLine();
            System.out.println("Enter the email");
            String email = scanner.nextLine();
            try {
                User user = new User(name, email);
                users.add(user);
            } catch (IllegalArgumentException e) {
                System.out.println("Exception!!!");
            }
            System.out.println("Enter 1, if is Finished, 2 - to print");
            String choice = scanner.nextLine();
            if (choice.equals("2")) {
                users.stream().forEach(System.out::println);
            }
            if (choice.equals("1")) {
                isFinished = true;
            }
        }
    }
}
