package com.example.demo.core.validation;

public class IsAlreadyExistException extends RuntimeException {

    public IsAlreadyExistException(String message) {
        super(message);
    }
}
