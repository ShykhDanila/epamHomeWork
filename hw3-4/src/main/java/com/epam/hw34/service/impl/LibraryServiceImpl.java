package com.epam.hw34.service.impl;

import com.epam.hw34.controller.dto.AuthorDto;
import com.epam.hw34.controller.dto.BookDto;
import com.epam.hw34.controller.dto.LibraryDto;
import com.epam.hw34.service.exception.EntityNotFoundException;
import com.epam.hw34.service.mapper.AuthorMapper;
import com.epam.hw34.service.mapper.BookMapper;
import com.epam.hw34.service.mapper.LibraryMapper;
import com.epam.hw34.service.model.Author;
import com.epam.hw34.service.model.Book;
import com.epam.hw34.service.model.Library;
import com.epam.hw34.service.repository.AuthorRepository;
import com.epam.hw34.service.repository.BookRepository;
import com.epam.hw34.service.repository.LibraryRepository;
import com.epam.hw34.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepo;
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;

    @Override
    public LibraryDto createLibrary(LibraryDto newLibrary) {
        log.info("create Library with adress {}", newLibrary.getAdress());
        Library library = LibraryMapper.INSTANCE.mapLibrary(newLibrary);
        library = libraryRepo.createLibrary(library);
        return LibraryMapper.INSTANCE.mapLibraryDto(library);
    }

    @Override
    public LibraryDto addBook(String libraryName, String bookTitle) {
        Optional<Library> library =libraryRepo.getLibraryByName(libraryName);
        if (!library.isPresent()){
            log.warn("dont exist's library with name {}", libraryName);
            throw new EntityNotFoundException(format("Dont exist's book with title {}", bookTitle));
        }
        Optional<Book> book = bookRepo.getByTitle(bookTitle);
        if (!book.isPresent()){
            log.warn("dont exist's book with title {}", bookTitle);
            throw new EntityNotFoundException(format("Dont exist's book with title {}", bookTitle));
        }
        Library lib = libraryRepo.addBook(library.get(), book.get());
        log.info("library {} add book {}", lib.getName(), book.get().getTitle());
        return LibraryMapper.INSTANCE.mapLibraryDto(lib);
    }

    @Override
    public Set<LibraryDto> getLibraryByBookTitle(String bookTitle) {
        log.info("get Library by book title {}", bookTitle);
        Optional<Book> book = bookRepo.getByTitle(bookTitle);
        if (!book.isPresent()){
            System.out.println(book.get());
            log.warn("dont exist's book with title {}", bookTitle);
            throw new EntityNotFoundException(format("Dont exist's book with title {}", bookTitle));
        }
        Set<Library> libraries = libraryRepo.getLibraryByBook(book.get());
        return LibraryMapper.INSTANCE.mapLibraryDtos(libraries);
    }

    @Override
    public AuthorDto getAuthorInfo(String authorId) {
        Author author = authorRepo.getById(authorId);
        if (author == null) {
            throw new EntityNotFoundException(format("Author with id %s not found", authorId));
        }
        return AuthorMapper.INSTANCE.mapAuthorDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author newAuthor = authorRepo.create(AuthorMapper.INSTANCE.mapAuthor(authorDto));
        return AuthorMapper.INSTANCE.mapAuthorDto(newAuthor);
    }

    @Override
    public Set<BookDto> getAuthorBooks(String authorId) {
        Author author = authorRepo.getById(authorId);
        if (author == null) {
            throw new EntityNotFoundException(format("Author with id %s not found", authorId));
        }

        Set<Book> books = author.getBooks();
        return BookMapper.INSTANCE.mapBookDtos(books);
    }

    @Override
    public BookDto createBook(String authorId, BookDto bookDto) {
        Author author = authorRepo.getById(authorId);
        if (author == null) {
            throw new EntityNotFoundException(format("Author with id %s not found", authorId));
        }
        Book newBook = BookMapper.INSTANCE.mapBook(bookDto);
        newBook.setAuthorId(authorId);
        newBook = bookRepo.createBook(newBook);
        return BookMapper.INSTANCE.mapBookDto(newBook);
    }
}
