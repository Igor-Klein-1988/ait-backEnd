package service;

//В коде написанном на уроке, реализовать класс UserServiceImpl который должен реализовывать
//следующие возможности: добавть User, найти всех User с заданным именем, получить список
// всех User. Для реализации этих функций класс UserServiceImpl может и должен обращаться к
// методам репозитория.

import model.User;

import java.util.List;

public interface UserService {
    String NULL_USER_ERROR_MESSAGE = "User cannot be null";
    String INVALID_EMAIL_ERROR_MESSAGE = "Invalid email address";
    String DUPLICATE_EMAIL_ERROR_MESSAGE = "Another user with the same email already exists";

    boolean addUser(User user);
    List<User> findByName(String name);
    List<User> findAll();
}
