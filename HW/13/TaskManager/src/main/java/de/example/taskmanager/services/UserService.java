package de.example.taskmanager.services;

import de.example.taskmanager.dto.user.NewUserDto;
import de.example.taskmanager.dto.user.UpdateUserDto;
import de.example.taskmanager.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(NewUserDto newUser);

    UserDto getUser(Integer id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Integer id, UpdateUserDto updateUser);
}
