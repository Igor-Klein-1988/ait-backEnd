package de.example.taskmanager.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "User", description = "Info about user")
public class UserDto {
    @Schema(description = "user id", example = "4")
    private Long id;
    @Schema(description = "name", example = "John")
    private String firstName;
    @Schema(description = "lastName", example = "Smith")
    private String lastName;
    @Schema(description = "email", example = "john.smith@gmail.com")
    private String email;
    @Schema(description = "password", example = "johnS!123")
    private String password;
}
