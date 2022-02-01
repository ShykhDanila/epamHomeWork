package com.epam.hw34.controller;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.dto.BookDto;
import com.epam.hw34.dto.LibraryDto;
import com.epam.hw34.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class LibraryController {

    private final LibraryService libraryService;

    @ApiOperation("Create Library")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LibraryDto createLibrary(@RequestBody LibraryDto libraryDto) {
        return libraryService.createLibrary(libraryDto);
    }

    @ApiOperation("Library add book")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "{libraryName}/book/{bookTitle}")
    public LibraryDto addBook(@RequestParam String libraryName, @RequestParam String bookTitle) {
        return libraryService.addBook(libraryName, bookTitle);
    }

    @ApiOperation("Get library by books title")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/book/{bookTitle}")
    public Set<LibraryDto> getLibraryByBookTitle(@PathVariable String bookTitle) {
        return libraryService.getLibraryByBookTitle(bookTitle);
    }

    @ApiOperation("Get author by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/author/{authorId}")
    public AuthorDto getAuthorById(@PathVariable String authorId) {
        return libraryService.getAuthorInfo(authorId);
    }

    @ApiOperation("Create author")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/author")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return libraryService.createAuthor(authorDto);
    }

    @ApiOperation("Get author books")
    @GetMapping(value = "/author/{authorId}/book")
    public Set<BookDto> getAuthorBooks(@PathVariable String authorId) {
        return libraryService.getAuthorBooks(authorId);
    }

    @ApiOperation("Add author books")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/author/{authorId}/book")
    public BookDto createBook(@PathVariable String authorId, @RequestBody BookDto bookDto) {
        return libraryService.createBook(authorId, bookDto);
    }

}
