package com.example.demo.core.util;


import com.example.demo.DTO.ToDoDtoRequest;
import com.example.demo.DTO.ToDoDtoResponse;
import com.example.demo.entity.ToDo;
import com.example.demo.entity.User;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.core.validation.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoConverters {

    private final ToDoRepository repository;
    private final UserRepository userRepository;

    public ToDo converterFromRequestToTodo(ToDoDtoRequest request) {

        Optional<User> author = userRepository.findById(request.getUserId());

        if (author.isEmpty()) {
            throw new NotFoundException("Request user id not found");
        }

        ToDo entity = new ToDo();
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setCreationDate(LocalDateTime.now());
        entity.setAuthor(author.get());
        return entity;
    }

    public ToDoDtoResponse converterFromTodoToResponse(ToDo entity) {

        ToDoDtoResponse response = new ToDoDtoResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setCreationDate(entity.getCreationDate());
        response.setUserId(entity.getAuthor().getId());
        return response;
    }
}
