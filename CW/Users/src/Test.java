import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User u1 = new User("44", "rr@44");
        User u2 = new User("55", "ww@55");
        users.add(u1);
        users.add(u2);
        users.stream().forEach(System.out::println);
        u2.setName("777");
        System.out.println("///////");
        System.out.println("///////");
        users.stream().forEach(System.out::println);
    }
}
