package com.example.demo.сontrollers;

import com.example.demo.DTO.UserDtoRequest;
import com.example.demo.DTO.UserDtoResponse;
import com.example.demo.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserControllers {
    private final UserServices service;

    @PostMapping
    public UserDtoResponse addUser(@RequestBody UserDtoRequest userDto) {
        return service.createUser(userDto);
    }

    @GetMapping("/all")
    public List<UserDtoResponse> findAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/byEmail")
    public UserDtoResponse findUserByEmail(@RequestParam String email) {
        return service.findUserByEmail(email);
    }
}
