package com.example.demo.repository;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User entity);

    List<User> findAll();

    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);


}
