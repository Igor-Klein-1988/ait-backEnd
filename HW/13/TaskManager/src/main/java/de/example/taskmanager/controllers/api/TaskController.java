package de.example.taskmanager.controllers.api;

import de.example.taskmanager.dto.task.NewTaskDto;
import de.example.taskmanager.dto.task.TaskDto;
import de.example.taskmanager.dto.task.UpdateTaskDto;
import de.example.taskmanager.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/task")
@Tags(value = @Tag(name = "Tasks"))
public class TaskController {
    private final TaskService service;

    @Operation(summary = "add Task")
    @PostMapping
    public TaskDto addTask(@RequestBody NewTaskDto newTask) {
        return service.addTask(newTask);
    }

    @Operation(summary = "find task by task id")
    @GetMapping("/{id}")
    public TaskDto findTaskById(@Parameter(description = "task id", example = "4")
                                @PathVariable("id") Integer id) {
        return service.getTask(id);
    }

    @Operation(summary = "find tasks by user id")
    @GetMapping("user/{id}")
    public List<TaskDto> findTasksByUserId(@Parameter(description = "user id", example = "4")
                                @PathVariable("id") Integer id) {
        return service.getTasksByUserId(id);
    }

    @Operation(summary = "get all tasks")
    @GetMapping
    public List<TaskDto> getAllTasks() {
        return service.getAllTasks();
    }

    @Operation(summary = "task update", description = "without id")
    @PutMapping("/{id}")
    public TaskDto updateTask(@Parameter(description = "task id", example = "4")
                              @PathVariable("id") Integer id,
                              @RequestBody UpdateTaskDto updateTask) {
        return service.updateTask(id, updateTask);
    }

    @Operation(summary = "deleting a task")
    @DeleteMapping("/{id}")
    public boolean deleteTask(@Parameter(description = "task id", example = "4")
                              @PathVariable("id") Integer id) {
        return service.deleteTask(id);
    }
}
