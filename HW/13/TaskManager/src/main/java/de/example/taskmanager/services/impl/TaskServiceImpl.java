package de.example.taskmanager.services.impl;

import de.example.taskmanager.dto.task.NewTaskDto;
import de.example.taskmanager.dto.task.TaskDto;
import de.example.taskmanager.dto.task.UpdateTaskDto;
import de.example.taskmanager.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public TaskDto addTask(NewTaskDto newTask) {
        return null;
    }

    @Override
    public TaskDto getTask(Integer id) {
        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return null;
    }

    @Override
    public TaskDto updateTask(Integer id, UpdateTaskDto updateTask) {
        return null;
    }

    @Override
    public boolean deleteTask(Integer id) {
        return false;
    }

    @Override
    public List<TaskDto> getTasksByUserId(Integer id) {
        return null;
    }
}
