package de.example.taskmanager.services;

import de.example.taskmanager.dto.task.NewTaskDto;
import de.example.taskmanager.dto.task.TaskDto;
import de.example.taskmanager.dto.task.UpdateTaskDto;

import java.util.List;

public interface TaskService {
    TaskDto addTask(NewTaskDto newTask);

    TaskDto getTask(Integer id);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(Integer id, UpdateTaskDto updateTask);

    boolean deleteTask(Integer id);

    List<TaskDto> getTasksByUserId(Integer id);
}
