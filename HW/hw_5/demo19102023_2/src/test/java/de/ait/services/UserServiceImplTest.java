package de.ait.services;

import de.ait.model.User;
import de.ait.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    public String EXIST_USER_EMAIL = "jack3@mail.com";
    public String NOT_EXIST_USER_EMAIL = "jack6@mail.com";
    UserRepository repository;
    UserService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UserRepository.class);
        service = new UserServiceImpl(repository);

        Mockito.when(repository.findAll()).thenReturn(List.of(
                        new User(1L, "jack1", "jack1@mail.com"),
                        new User(2L, "jack2", "jack2@mail.com"),
                        new User(3L, "jack3", "jack3@mail.com"),
                        new User(4L, "jack4", "jack4@mail.com")
                )
        );

        Mockito.when(repository.findByEmail(EXIST_USER_EMAIL)).thenReturn(new User(3L, "jack3", "jack3@mail.com"));
        Mockito.when(repository.findByEmail(NOT_EXIST_USER_EMAIL)).thenReturn(null);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        List<User> expected = List.of(
                new User(1L, "jack1", "jack1@mail.com"),
                new User(2L, "jack2", "jack2@mail.com"),
                new User(3L, "jack3", "jack3@mail.com"),
                new User(4L, "jack4", "jack4@mail.com")
        );

        List<User> actual = service.getAllUsers();

        Assertions.assertEquals(expected, actual);

    }

    @Nested
    @DisplayName("test createUser()")
    class CreateUser {
        @Test
        void create_user_with_not_existing_email() {
            String name = "jack16";
            String email = NOT_EXIST_USER_EMAIL;

            service.createUser(name, email);

            Mockito.verify(repository, Mockito.times(1)).save(new User(name, email));
        }


        @Test
        void create_user_with_existing_email() {
            String name = "jack3";
            String email = EXIST_USER_EMAIL;

            Assertions.assertAll(
                    () -> Assertions.assertThrows(RuntimeException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
            //Mockito.verify(repository, Mockito.times(1)).save(new User(name, email));
        }

        @Test
        void create_user_with_blank_name() {
            String name = "";
            String email = NOT_EXIST_USER_EMAIL;
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IllegalArgumentException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
        }

        @Test
        void create_user_with_null_name() {
            String name = null;
            String email = NOT_EXIST_USER_EMAIL;
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IllegalArgumentException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
        }

        @Test
        void create_user_with_blank_email() {
            String name = "new name";
            String email = "";
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IllegalArgumentException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
        }

        @Test
        void create_user_with_null_email() {
            String name = "new name";
            String email = null;
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IllegalArgumentException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
        }

        @Test
        void create_user_without_at() {
            String name = "new name";
            String email = "aaaamail.com";
            Assertions.assertAll(
                    () -> Assertions.assertThrows(IllegalArgumentException.class, () -> service.createUser(name, email)), // wait Exception
                    () -> Mockito.verify(repository, Mockito.never()).save(Mockito.any())
            );
        }
    }

    @Nested
    @DisplayName("test updateUser()")
    class UpdateUser {
        @Test
        void update_existing_user() {
            Long userId = 1L;
            String newName = "newName";
            String newEmail = "newEmail@mail.com";

            User existingUser = new User(userId, "oldName", "oldEmail@mail.com");
            Mockito.when(repository.findByID(userId)).thenReturn(existingUser);

            service.updateUser(userId, newName, newEmail);

            Assertions.assertAll(
                    () -> Assertions.assertEquals(userId, existingUser.getId()),
                    () -> Assertions.assertEquals(newName, existingUser.getName()),
                    () -> Assertions.assertEquals(newEmail, existingUser.getEmail()),
                    () -> Mockito.verify(repository, Mockito.times(1)).save(existingUser)
            );
        }

        @Test
        void update_user_with_existing_email() {
            Long userId = 1L;
            String newName = "newName";
            String email = EXIST_USER_EMAIL;

            Assertions.assertAll(
                    () -> Assertions.assertThrows(RuntimeException.class, () -> service.updateUser(userId, newName, email)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }

        @Test
        void update_non_existing_user() {
            Long userId = 55L;
            String newName = "newName";
            String newEmail = "newEmail@mail.com";

            Mockito.when(repository.findByID(userId)).thenReturn(null);

            service.updateUser(userId, newName, newEmail);

            Assertions.assertAll(
                    () -> Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> service.updateUser(userId, newName, newEmail)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }

        @Test
        void update_user_with_blank_name() {
            Long userId = 1L;
            String newName = "";
            String newEmail = "newEmail@mail.com";

            Assertions.assertAll(
                    () -> Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> service.updateUser(userId, newName, newEmail)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }

        void update_user_with_null_name() {
            Long userId = 1L;
            String newName = null;
            String newEmail = "newEmail@mail.com";

            Assertions.assertAll(
                    () -> Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> service.updateUser(userId, newName, newEmail)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }

        @Test
        void update_user_with_blank_email() {
            Long userId = 3L;
            String newName = "newName";
            String newEmail = "";

            Assertions.assertAll(
                    () -> Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> service.updateUser(userId, newName, newEmail)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }

        @Test
        void update_user_with_null_email() {
            Long userId = 3L;
            String newName = "newName";
            String newEmail = null;

            Assertions.assertAll(
                    () -> Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> service.updateUser(userId, newName, newEmail)),
                    () -> Mockito.verify(repository, Mockito.never()).update(Mockito.any())
            );
        }
    }
}