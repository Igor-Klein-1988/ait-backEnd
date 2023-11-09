package de.example.taskmanager.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private User author;

}
