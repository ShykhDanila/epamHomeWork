package com.epam.hw34.service.exception;

public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
