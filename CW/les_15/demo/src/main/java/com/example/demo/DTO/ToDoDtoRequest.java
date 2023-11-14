package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ToDoDtoRequest {
    private String title;
    private String description;
    private Integer userId;

}
