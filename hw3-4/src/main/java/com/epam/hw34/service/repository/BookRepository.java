package com.epam.hw34.service.repository;

import com.epam.hw34.service.model.Book;

import java.util.Optional;
import java.util.Set;

public interface BookRepository {

    Book createBook(Book newBook);

    Book getById(String bookId);

    Set<Book> getBooksByAuthor(String authorId);

    Optional<Book> getByTitle(String bookTitle);
}

