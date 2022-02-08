package com.epam.hw35.service.exception;

public class LibraryAlreadyExistsException extends RuntimeException {

    public LibraryAlreadyExistsException(String message) {
        super(message);
    }
}
