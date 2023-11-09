package de.example.taskmanager.dto.task;

import de.example.taskmanager.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UpdateTask", description = "Info for update task")
public class UpdateTaskDto {
    @Schema(description = "Title", example = "Write a letter")
    private String title;
    @Schema(description = "Description", example = "Write a letter to Smith about our project updates.")
    private String description;
    @Schema(description = "Date of creation", example = "2023-03-15T14:30:00Z")
    private String creationDate;
    @Schema(description = "Author of the task")
    private UserDto author;
}
