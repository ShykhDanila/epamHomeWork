package com.epam.hw34.service;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.dto.BookDto;
import com.epam.hw34.dto.LibraryDto;

import java.util.Set;

public interface LibraryService {

    LibraryDto createLibrary(LibraryDto newLibrary);

    LibraryDto addBook(String libraryId, BookDto bookDto);

    Set<LibraryDto> getLibraryByBookId(String bookId);

    AuthorDto getAuthorInfo(String authorId);

    AuthorDto createAuthor(AuthorDto authorDto);

    Set<BookDto> getAuthorBooks(String authorId);

    BookDto createBook(String authorId, BookDto bookDto);
}
