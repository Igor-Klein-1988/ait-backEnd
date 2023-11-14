package com.example.demo.сontrollers;

import com.example.demo.DTO.ToDoDtoRequest;
import com.example.demo.DTO.ToDoDtoResponse;
import com.example.demo.services.TodoServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class ToDoControllers {
    private final TodoServices todoServices;

    @PostMapping()
    public ToDoDtoResponse addTodo(@RequestBody ToDoDtoRequest request){
        return todoServices.addTodo(request);
    }

    @GetMapping("/{id}")
    public ToDoDtoResponse findTodoById(@PathVariable("id") Integer id){
        return todoServices.findById(id);
    }

    @GetMapping("/all")
    public List<ToDoDtoResponse> findAllTodos(){
        return todoServices.findAll();
    }

    @GetMapping("/user")
    public List<ToDoDtoResponse> findTodoByUserId(@RequestParam Integer userId){
        return todoServices.findTodoByUserId(userId);
    }

    @PutMapping("/{id}")
    public ToDoDtoResponse updateTodo(@PathVariable ("id") Integer id,  @RequestBody ToDoDtoRequest updateRequest){
        return todoServices.updateToDo(id, updateRequest);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTodoById(@PathVariable Integer id){
        return todoServices.delete(id);
    }


}
