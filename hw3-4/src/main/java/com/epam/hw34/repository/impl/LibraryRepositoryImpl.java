package com.epam.hw34.repository.impl;

import com.epam.hw34.model.Book;
import com.epam.hw34.model.Library;
import com.epam.hw34.repository.BookRepository;
import com.epam.hw34.repository.LibraryRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
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
    public Library addBook(String libraryId, Book book) {
        Library library = libraries.get(libraryId);
        library.getBooks().add(book);
        return library;
    }

    @Override
    public Set<Library> getLibraryByBookId(String bookId) {
        Book book = bookRepository.getById(bookId);
        return libraries.values().stream()
                .filter(lib -> lib.getBooks().equals(book))
                .collect(Collectors.toSet());
    }
}
