package com.epam.hw34.service.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
