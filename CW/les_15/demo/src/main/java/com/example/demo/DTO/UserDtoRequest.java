package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDtoRequest {
    private String username;
    private String password;
    private String email;

}
