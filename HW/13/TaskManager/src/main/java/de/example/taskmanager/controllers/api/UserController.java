package de.example.taskmanager.controllers.api;

import de.example.taskmanager.dto.user.NewUserDto;
import de.example.taskmanager.dto.user.UpdateUserDto;
import de.example.taskmanager.dto.user.UserDto;
import de.example.taskmanager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tags(value = @Tag(name = "Users"))
public class UserController {
    private final UserService service;

    @Operation(summary = "add User", description = "need add fields")
    @PostMapping
    public UserDto addUser(@RequestBody NewUserDto newUser) {
        return service.addUser(newUser);
    }

    @Operation(summary = "find user by id")
    @GetMapping("/{id}")
    public UserDto findUserById(@Parameter(description = "user id", example = "4")
                                @PathVariable("id") Integer id) {
        return service.getUser(id);
    }

    @Operation(summary = "get all users")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @Operation(summary = "user update", description = "without id")
    @PutMapping("/{id}")
    public UserDto updateUser(@Parameter(description = "user id", example = "4")
                              @PathVariable("id") Integer id,
                              @RequestBody UpdateUserDto updateUser) {
        return service.updateUser(id, updateUser);
    }
}
