package de.example.taskmanager.services.impl;

import de.example.taskmanager.dto.user.NewUserDto;
import de.example.taskmanager.dto.user.UpdateUserDto;
import de.example.taskmanager.dto.user.UserDto;
import de.example.taskmanager.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto addUser(NewUserDto newUser) {
        return null;
    }

    @Override
    public UserDto getUser(Integer id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto updateUser(Integer id, UpdateUserDto updateUser) {
        return null;
    }
}
