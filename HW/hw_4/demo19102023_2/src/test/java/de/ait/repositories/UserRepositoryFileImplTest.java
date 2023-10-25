package de.ait.repositories;

import de.ait.model.User;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryFileImplTest {
    public static final String TEST_FILE = "users_test.txt";
    UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepositoryFileImpl(TEST_FILE);
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        try {
            boolean newFile = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("create file error");
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Test normal save")
    void save() throws Exception {
        // подготовка исзодных данных
        // ожидаемый рез.
        // вызываем тестируемый метод
        // сравнить ожилания и результат метода

        User user = new User("qwer", "qwer@qwert.com");
        String expected = "1;qwer;qwer@qwert.com";
        repository.save(user);

        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            String result = bf.readLine();
            Assertions.assertEquals(expected, result);
        }
    }

    @Test
    void testSaveUser_UniqueID() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        repository.save(user1);
        repository.save(user2);

        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    void testSaveUser_DataFormat() {
        String name = "Data Format Test";
        String email = "data@test.com";
        User user = new User(name, email);
        repository.save(user);

        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE))) {
            String line = br.readLine();
            String[] parts = line.split(";");

            assertEquals(3, parts.length);

            try {
                Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                fail("ID is not a number");
            }
            assertEquals(name, parts[1]);
            assertEquals(email, parts[2]);
        } catch (IOException e) {
            fail("IOException occurred");
        }
    }

    @Test
    void testSaveUser_EmptyData() {
        User user = new User("", "");
        assertDoesNotThrow(() -> {
            repository.save(user);
        });
    }

    // findByID
    @Test
    void testFindByIdExistingUser() {
        String name = "Data Format Test";
        String email = "data@test.com";
        User user1 = new User(name, email);
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        User user = repository.findByID(user1.getId());

        assertNotNull(user);
        assertEquals(user1.getId(), user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testFindByIdNonExistentUser() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        User user = repository.findByID(444L);

        assertNull(user);
    }

    @Test
    void testFindByIdUnreadableFile() {
        User user = new User("User 3", "user3@example.com");
        repository.save(user);
        makeFileUnreadable(TEST_FILE);

        User foundUser = repository.findByID(user.getId());

        assertNull(foundUser);
    }

    // findAll()
    @Test
    void testFindAllValidFile() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        List<User> users = repository.findAll();

        assertFalse(users.isEmpty());
        assertEquals(3, users.size());
        assertEquals(user1.getName(), users.get(0).getName());
        assertEquals(user3.getEmail(), users.get(2).getEmail());
    }

    @Test
    void testFindAllUnreadableFile() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);
        makeFileUnreadable(TEST_FILE);

        List<User> users = repository.findAll();

        assertTrue(users.isEmpty());
    }

    // deleteById()
    @Test
    void testDeleteById() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        Long idToDelete = user2.getId();
        repository.deleteById(idToDelete);

        User deletedUser = repository.findByID(idToDelete);
        assertNull(deletedUser);
        List<User> users = repository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void testDeleteNonExistentId() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        Long nonExistentId = 10L;
        repository.deleteById(nonExistentId);

        List<User> users = repository.findAll();
        assertEquals(3, users.size());
    }

    // update

    @Test
    void testUpdateExistingUser() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        String name = "Updated User";
        String email = "updated@example.com";
        User updatedUser = new User(user2.getId(), name, email);

        repository.update(updatedUser);

        User retrievedUser = repository.findByID(user2.getId());
        assertNotNull(retrievedUser);
        assertEquals(updatedUser, retrievedUser);
    }

    @Test
    void testUpdateNonExistentUser() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        User nonExistentUser = new User(10L, "Non-Existent User", "nonexistent@example.com");

        repository.update(nonExistentUser);

        List<User> users = repository.findAll();
        assertEquals(3, users.size());
    }

    @Test
    void testUpdateWithMalformedData() {
        User user1 = new User("User 1", "user1@example.com");
        String name = "User 2";
        String email = "user2@example.com";
        User user2 = new User(name, email);
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        User userWithMalformedData = new User(user2.getId(), "Malformed User", "malformed-example.com");

        repository.update(userWithMalformedData);

        User retrievedUser = repository.findByID(user2.getId());
        assertNotNull(retrievedUser);
        assertNotEquals(userWithMalformedData.getEmail(), user2.getEmail());
        assertNotEquals(userWithMalformedData.getName(), user2.getName());
    }

    // findByEmail
    @Test
    void testFindByEmail() {
        User user1 = new User("User 1", "user1@example.com");
        String name = "User 2";
        String emailToFind = "user2@example.com";
        User user2 = new User(name, emailToFind);
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        User foundUser = repository.findByEmail(emailToFind);

        assertNotNull(foundUser);
        assertEquals(foundUser.getEmail(), user2.getEmail());
        assertEquals(foundUser.getId(), user2.getId());
        assertEquals(foundUser.getName(), user2.getName());
    }

    @Test
    void testFindByNonExistentEmail() {
        User user1 = new User("User 1", "user1@example.com");
        User user2 = new User("User 2", "user2@example.com");
        User user3 = new User("User 3", "user3@example.com");
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        String nonExistentEmail = "nonexistent@example.com";
        User foundUser = repository.findByEmail(nonExistentEmail);

        assertNull(foundUser);
    }

    private void makeFileUnreadable(String filePath) {
        Path file = FileSystems.getDefault().getPath(filePath);

        // Создаем множество прав доступа, убирая право на чтение
        Set<PosixFilePermission> permissions = new HashSet<>();
        permissions.add(PosixFilePermission.OWNER_WRITE);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_WRITE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_WRITE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);

        try {
            // Устанавливаем новые права доступа к файлу
            Files.setPosixFilePermissions(file, permissions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}