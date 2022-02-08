package com.epam.hw34.service.exception;

public class LibraryAlreadyExistsException extends RuntimeException {

    public LibraryAlreadyExistsException(String message) {
        super(message);
    }
}
