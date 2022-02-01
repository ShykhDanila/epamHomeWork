package com.epam.hw34.service.impl;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.dto.BookDto;
import com.epam.hw34.dto.LibraryDto;
import com.epam.hw34.exception.EntityNotFoundException;
import com.epam.hw34.mapper.AuthorMapper;
import com.epam.hw34.mapper.BookMapper;
import com.epam.hw34.mapper.LibraryMapper;
import com.epam.hw34.model.Author;
import com.epam.hw34.model.Book;
import com.epam.hw34.model.Library;
import com.epam.hw34.repository.AuthorRepository;
import com.epam.hw34.repository.BookRepository;
import com.epam.hw34.repository.LibraryRepository;
import com.epam.hw34.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public LibraryDto addBook(String libraryId, BookDto bookDto) {
        Book book = BookMapper.INSTANCE.mapBook(bookDto);
        Library library = libraryRepo.addBook(libraryId, book);
        log.info("library {} add book {}", library.getName(), book.getTitle());
        return LibraryMapper.INSTANCE.mapLibraryDto(library);
    }

    @Override
    public Set<LibraryDto> getLibraryByBookId(String bookId) {
        log.info("get Library by book id {}", bookId);
        Set<Library> libraries = libraryRepo.getLibraryByBookId(bookId);
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
