package de.ait.task.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskControllerWeb {
    @GetMapping("/task")
    public String showTask(Model model) {
        model.addAttribute("taskText", "Создать SpringBoot приложение, которое по обращению localhost:8080/task выводит страницу с текстом этого ДЗ");
        return "task";
    }
}
