package com.example.demo.core.validation;

import com.example.demo.DTO.ToDoDtoRequest;

public class ToDoDescriptionMaxLengthValidation {

    public void validation(ToDoDtoRequest request){
        if (request.getDescription().length() > 30) {
            throw new IllegalStateException("ToDo description length more than 30 ...");
        }
    }
}
