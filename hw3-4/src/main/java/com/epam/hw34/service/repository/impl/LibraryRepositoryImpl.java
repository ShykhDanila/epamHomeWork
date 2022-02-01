package com.epam.hw34.service.repository.impl;

import com.epam.hw34.service.model.Book;
import com.epam.hw34.service.model.Library;
import com.epam.hw34.service.repository.BookRepository;
import com.epam.hw34.service.repository.LibraryRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LibraryRepositoryImpl implements LibraryRepository {

    private Map<String, Library> libraries = new HashMap<>();
    private final BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public Library createLibrary(Library newLibrary) {
        String id = UUID.randomUUID().toString();
        newLibrary.setId(id);
        libraries.put(id, newLibrary);
        return newLibrary;
    }

    @Override
    public Library addBook(Library library, Book book) {
        library.getBooks().add(book);
        return library;
    }

    @Override
    public Optional<Library> getLibraryByName(String nameLibrary) {
        return libraries.values().stream()
                .filter(lib -> lib.getName().equals(nameLibrary))
                .findFirst();
    }

    @Override
    public Set<Library> getLibraryByBook(Book book) {
        return libraries.values().stream()
                .filter(lib -> lib.getBooks().contains(book))
                .collect(Collectors.toSet());
    }
}
