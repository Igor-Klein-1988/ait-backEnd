package com.example.hw.javaConfig;

import com.example.hw.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Student getStudent() {
        String name = "Student from AppConfig";
        String age = "20";
        return new Student(name, age);
    }
}
