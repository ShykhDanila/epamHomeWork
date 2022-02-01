package com.epam.hw34.controller;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.dto.BookDto;
import com.epam.hw34.dto.LibraryDto;
import com.epam.hw34.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createLibrary(libraryDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/book/{libraryId}")
    public LibraryDto addBook(@PathVariable String libraryId, @RequestBody BookDto bookDto) {
        return libraryService.addBook(libraryId, bookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/book/{bookId}")
    public Set<LibraryDto> getLibraryByBookId(@PathVariable String bookId) {
        return libraryService.getLibraryByBookId(bookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/author/{authorId}")
    public AuthorDto getAuthorById(@PathVariable String authorId) {
        return libraryService.getAuthorInfo(authorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/author")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return libraryService.createAuthor(authorDto);
    }

    @GetMapping(value = "/author/{authorId}/book")
    public Set<BookDto> getAuthorBooks(@PathVariable String authorId) {
        return libraryService.getAuthorBooks(authorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/author/{authorId}/book")
    public BookDto createBook(@PathVariable String authorId, @RequestBody BookDto bookDto) {
        return libraryService.createBook(authorId, bookDto);
    }

}
