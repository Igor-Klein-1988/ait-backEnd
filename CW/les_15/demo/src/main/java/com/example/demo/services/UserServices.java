package com.example.demo.services;

import com.example.demo.DTO.UserDtoRequest;
import com.example.demo.DTO.UserDtoResponse;
import com.example.demo.core.util.UserConverters;
import com.example.demo.core.validation.IsAlreadyExistException;
import com.example.demo.core.validation.NotFoundException;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServices {
    private final UserRepository userRepository;
    private final UserConverters converters;


    // Получить список всех пользователей

    public List<UserDtoResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(converters::converterFromUserToResponse)
                .toList();
    }

    //Найти пользователя по email

    public UserDtoResponse findUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return converters.converterFromUserToResponse(userOptional.get());
        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
    }

    // Создать нового пользователя

    public UserDtoResponse createUser(UserDtoRequest request){
        System.out.println(userRepository.findByEmail(request.getEmail()).isEmpty());
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            User newUser = converters.converterFromRequestToUser(request);

            User sevedUser = userRepository.save(newUser);
            return converters.converterFromUserToResponse(sevedUser);
        } else {
            throw new IsAlreadyExistException("Manager with email: " + request.getEmail() + " is already exist!");
        }
    }

}