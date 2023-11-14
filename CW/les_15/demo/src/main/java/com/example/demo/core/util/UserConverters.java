package com.example.demo.core.util;

import com.example.demo.DTO.UserDtoRequest;
import com.example.demo.DTO.UserDtoResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.core.validation.IsAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserConverters {

    private final UserRepository userRepository;

    public User converterFromRequestToUser(UserDtoRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new IsAlreadyExistException("Request user id already exist.");
        }

        User entity = new User();
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
        entity.setEmail(request.getEmail());
        entity.setCreationDate(LocalDateTime.now());
        entity.setLastVisitDate(LocalDateTime.now());
        return entity;
    }

    public UserDtoResponse converterFromUserToResponse(User entity) {

        UserDtoResponse response = new UserDtoResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setCreationDate(entity.getCreationDate());
        response.setLastVisitDate(entity.getLastVisitDate());
        return response;
    }
}

