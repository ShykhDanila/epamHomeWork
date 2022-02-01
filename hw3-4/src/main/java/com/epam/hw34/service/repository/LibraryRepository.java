package com.epam.hw34.service.repository;


import com.epam.hw34.service.model.Book;
import com.epam.hw34.service.model.Library;

import java.util.Optional;
import java.util.Set;

public interface LibraryRepository {
    Library createLibrary(Library newLibrary);

    Library addBook(Library library, Book book);

    Optional<Library> getLibraryByName(String nameLibrary);

    Set<Library> getLibraryByBook(Book book);
}
