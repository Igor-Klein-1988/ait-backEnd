package test.service;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserServiceFileImpl;
import service.UserServiceListImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceListImplTest {
    private UserServiceListImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceListImpl();
    }

    @Test
    public void testAddUserWithNullUser() {
        try {
            userService.addUser(null);
            fail("Expected IllegalArgumentException for null user");
        } catch (IllegalArgumentException e) {
            assertEquals(UserServiceFileImpl.NULL_USER_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void testAddUserWithInvalidEmail() {
        User user = new User("John Doe", "invalid_email");

        try {
            userService.addUser(user);
            fail("Expected IllegalArgumentException for invalid email");
        } catch (IllegalArgumentException e) {
            assertEquals(UserServiceFileImpl.INVALID_EMAIL_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void testAddUserWithDuplicateEmail() {
        User user1 = new User("John Doe", "john.doe@example.com");
        User userWithDuplicateEmail = new User("Jane Smith", "john.doe@example.com");
        userService.addUser(user1);
        try {
            userService.addUser(userWithDuplicateEmail);
            fail("Expected IllegalArgumentException for duplicate email");
        } catch (IllegalArgumentException e) {
            assertEquals(UserServiceFileImpl.DUPLICATE_EMAIL_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void testAddUser() {
        User user = new User("John Doe", "john.doe@example.com");

        assertTrue(userService.addUser(user));
    }

    @Test
    public void testFindByNameSuccess() {
        String searchName = "John";
        User user1 = new User(1L, searchName, "john@example.com");
        User user2 = new User(2L, searchName, "jane@example.com");
        User user3 = new User(3L, "Alice", "alice@example.com");

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        List<User> foundUsers = userService.findByName(searchName);

        assertEquals(2, foundUsers.size());
        assertTrue(foundUsers.stream().anyMatch(user -> user.getName().equals(user1.getName())));

        assertTrue(foundUsers.contains(user1));
        assertTrue(foundUsers.contains(user2));

    }

    @Test
    public void testFindByNameNoResults() {
        User user1 = new User(1L, "John", "john@example.com");
        User user2 = new User(2L, "Jane", "jane@example.com");
        User user3 = new User(3L, "Alice", "alice@example.com");

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        List<User> foundUsers = userService.findByName("Bob");

        assertTrue(foundUsers.isEmpty());
    }

    @Test
    void findAll() {
        User user1 = new User(1L, "John", "john@example.com");
        User user2 = new User(2L, "Jane", "jane@example.com");
        User user3 = new User(3L, "Alice", "alice@example.com");

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        List<User> foundUsers = userService.findAll();

        assertEquals(3, foundUsers.size());
        assertTrue(foundUsers.contains(user1));
        assertTrue(foundUsers.contains(user2));
        assertTrue(foundUsers.contains(user3));
    }
}