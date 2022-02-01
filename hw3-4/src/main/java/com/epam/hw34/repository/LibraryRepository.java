package com.epam.hw34.repository;


import com.epam.hw34.model.Book;
import com.epam.hw34.model.Library;

import java.util.Optional;
import java.util.Set;

public interface LibraryRepository {
    Library createLibrary(Library newLibrary);

    Library addBook(Library library, Book book);

    Optional<Library> getLibraryByName(String nameLibrary);

    Set<Library> getLibraryByBook(Book book);
}
