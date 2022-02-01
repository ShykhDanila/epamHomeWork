package com.epam.hw34.service;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.dto.BookDto;
import com.epam.hw34.dto.LibraryDto;

import java.util.Set;

public interface LibraryService {

    LibraryDto createLibrary(LibraryDto newLibrary);

    LibraryDto addBook(String libraryName, String bookTitle);

    Set<LibraryDto> getLibraryByBookTitle(String bookTitle);

    AuthorDto getAuthorInfo(String authorId);

    AuthorDto createAuthor(AuthorDto authorDto);

    Set<BookDto> getAuthorBooks(String authorId);

    BookDto createBook(String authorId, BookDto bookDto);
}
