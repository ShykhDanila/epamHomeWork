package com.epam.hw34.repository;


import com.epam.hw34.model.Book;
import com.epam.hw34.model.Library;

import java.util.Set;

public interface LibraryRepository {
    Library createLibrary(Library newLibrary);

    Library addBook(String libraryId, Book book);

    Set<Library> getLibraryByBookId(String bookId);
}
