package com.epam.hw34.repository.impl;

import com.epam.hw34.model.Book;
import com.epam.hw34.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookRepositoryImpl implements BookRepository {

    private Map<String, Book> books = new HashMap<>();

    @Override
    public Book createBook(Book newBook) {
        String id = UUID.randomUUID().toString();
        newBook.setId(id);
        books.put(id, newBook);
        return newBook;
    }

    @Override
    public Book getById(String bookId) {
        return books.get(bookId);
    }

    @Override
    public Set<Book> getBooksByAuthor(String authorId) {
        return books.values().stream()
                .filter(b -> b.getAuthorId().equals(authorId))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Book> getByTitle(String bookTitle) {
        return books.values().stream()
                .filter(b -> b.getTitle().equals(bookTitle))
                .findFirst();
    }
}
