package com.epam.hw36.service;

import com.epam.hw36.controller.dto.AuthorDto;
import com.epam.hw36.controller.dto.BookDto;
import com.epam.hw36.controller.dto.LibraryDto;

import java.util.List;
import java.util.Set;

public interface LibraryService {

    LibraryDto createLibrary(LibraryDto newLibrary);

    LibraryDto getLibrary(String address);

    List<LibraryDto> getAllLibraries();

    LibraryDto addBook(String libraryName, String bookTitle);

    void deleteLibrary(String address);

    Set<LibraryDto> getLibraryByBookTitle(String bookTitle);

    AuthorDto getAuthorInfo(Long authorId);

    AuthorDto createAuthor(AuthorDto authorDto);

    Set<BookDto> getAuthorBooks(Long authorId);

    BookDto createBook(Long authorId, BookDto bookDto);
}
