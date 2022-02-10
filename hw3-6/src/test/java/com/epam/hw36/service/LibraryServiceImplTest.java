package com.epam.hw36.service;

import com.epam.hw36.controller.dto.AuthorDto;
import com.epam.hw36.controller.dto.BookDto;
import com.epam.hw36.controller.dto.LibraryDto;
import com.epam.hw36.service.exception.AuthorAlreadyExistsException;
import com.epam.hw36.service.exception.EntityNotFoundException;
import com.epam.hw36.service.exception.LibraryAlreadyExistsException;
import com.epam.hw36.service.impl.LibraryServiceImpl;
import com.epam.hw36.service.mapper.AuthorMapper;
import com.epam.hw36.service.mapper.BookMapper;
import com.epam.hw36.service.mapper.LibraryMapper;
import com.epam.hw36.service.model.Author;
import com.epam.hw36.service.model.Book;
import com.epam.hw36.service.model.Library;
import com.epam.hw36.service.repository.AuthorRepository;
import com.epam.hw36.service.repository.BookRepository;
import com.epam.hw36.service.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    private final Library library = getLibrary();
    private final LibraryDto libraryDto = getLibraryDto();
    private final Book book = getBook();
    private final BookDto bookDto = getBookDto();
    private final Author author = getAuthor();
    private final AuthorDto authorDto = getAuthorDto();

    @Test
    public void createLibraryTest() {
        //given
        when(libraryRepository.existsByAddress(library.getAddress())).thenReturn(false);
        when(libraryRepository.save(library)).thenReturn(library);

        //when
        LibraryDto actual = libraryService.createLibrary(libraryDto);

        //then
        assertEquals(libraryDto, actual);
    }

    @Test
    public void createLibraryWithExceptionTest() {
        //given
        when(libraryRepository.existsByAddress(library.getAddress())).thenReturn(true);

        //then
        assertThrows(LibraryAlreadyExistsException.class,
                () -> libraryService.createLibrary(libraryDto));
        verify(libraryRepository, only()).existsByAddress(libraryDto.getAddress());
        verify(libraryRepository, never()).save(any());
    }

    @Test
    void getLibraryTest() {
        //given
        when(libraryRepository.findByAddress(library.getAddress())).thenReturn(Optional.of(library));

        //when
        LibraryDto actual = libraryService.getLibrary(library.getAddress());

        //then
        assertEquals(libraryDto, actual);
    }

    @Test
    void getLibraryWithExceptionTest() {
        when(libraryRepository.findByAddress(library.getAddress())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.getLibrary(library.getAddress()));
    }

    @Test
    void getAllLibrariesTest() {
        //given
        when(libraryRepository.findAll(any(PageRequest.class))).thenReturn(Page.empty());

        //when
        List<LibraryDto> libraryDtos = libraryService.getAllLibraries();

        //then
        assertThat(libraryDtos, hasSize(0));
    }

    @Test
    void deleteLibraryTest() {
        //given
        when(libraryRepository.findByAddress(library.getAddress())).thenReturn(Optional.of(library));
        doNothing().when(libraryRepository).delete(library);

        //when
        libraryService.deleteLibrary(library.getAddress());

        //then
        verify(libraryRepository, times(1)).delete(library);
    }

    @Test
    void deleteLibraryWithExceptionTest() {
        when(libraryRepository.findByAddress(library.getAddress())).thenReturn(Optional.empty());
        //then
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.deleteLibrary(library.getAddress()));
        verify(libraryRepository, never()).delete(library);
    }

    @Test
    void getLibraryByBookTitleTest() {
        //geven
        when(bookRepository.getByTitle(book.getTitle())).thenReturn(Optional.of(book));
        when(libraryRepository.getLibraryByBooks(book)).thenReturn(Optional.of(Collections.singleton(library)));

        //when
        Set<LibraryDto> actual = libraryService.getLibraryByBookTitle(book.getTitle());

        //then
        assertThat(actual, hasItems(libraryDto));
    }

    @Test
    void getLibraryByBookTitleWithExceptionTest() {
        when(bookRepository.getByTitle(book.getTitle())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.getLibraryByBookTitle(book.getTitle()));
        verify(bookRepository, only()).getByTitle(book.getTitle());
        verify(libraryRepository, never()).getLibraryByBooks(any());
    }

    @Test
    void getLibraryByBookTitleWithExceptionTestV2() {
        when(bookRepository.getByTitle(book.getTitle())).thenReturn(Optional.of(book));
        when(libraryRepository.getLibraryByBooks(book)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> libraryService.getLibraryByBookTitle(book.getTitle()));
        verify(bookRepository, only()).getByTitle(book.getTitle());
        verify(libraryRepository, only()).getLibraryByBooks(any());
    }

    @Test
    void getAuthorInfoTest() {
        //given
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        //when
        AuthorDto expected = AuthorMapper.INSTANCE.mapAuthorDto(author);
        AuthorDto actual = libraryService.getAuthorInfo(author.getId());
        //then
        assertEquals(expected, actual);
    }

    @Test
    void getAuthorInfoWithExceptionTest() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.getAuthorInfo(author.getId()));
    }

    @Test
    void addBookTest() {
        //given
        when(libraryRepository.getLibraryByLibraryName(library.getLibraryName())).thenReturn(Optional.of(library));
        when(bookRepository.getByTitle(book.getTitle())).thenReturn(Optional.of(book));

        //when
        LibraryDto actual = libraryService.addBook(library.getLibraryName(), book.getTitle());

        //then
        assertEquals(libraryDto, actual);
    }

    @Test
    void addBookWithExceptionTest() {
        //given
        when(libraryRepository.getLibraryByLibraryName(library.getLibraryName())).thenReturn(Optional.empty());
        //then
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.addBook(library.getLibraryName(), book.getTitle()));
        verify(libraryRepository, only()).getLibraryByLibraryName(library.getLibraryName());
        verify(bookRepository, never()).getByTitle(any());
    }

    @Test
    void addBookWithExceptionTestV2() {
        //given
        when(libraryRepository.getLibraryByLibraryName(library.getLibraryName())).thenReturn(Optional.of(library));
        when(bookRepository.getByTitle(book.getTitle())).thenReturn(Optional.empty());
        //then
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.addBook(library.getLibraryName(), book.getTitle()));
        verify(libraryRepository, only()).getLibraryByLibraryName(library.getLibraryName());
        verify(bookRepository, only()).getByTitle(any());
    }

    @Test
    void createAuthorTest() {
        //given
        when(authorRepository.existsAuthorByAuthorNameAndNickname(authorDto.getName(), authorDto.getNickname())).thenReturn(false);
        when(authorRepository.save(author)).thenReturn(author);

        //when
        AuthorDto actual = libraryService.createAuthor(authorDto);

        //then
        assertEquals(authorDto, actual);
    }

    @Test
    void createAuthorWithExceptionTest() {
        when(authorRepository.existsAuthorByAuthorNameAndNickname(authorDto.getName(), authorDto.getNickname())).thenReturn(true);
        assertThrows(AuthorAlreadyExistsException.class,
                () -> libraryService.createAuthor(authorDto));
        verify(authorRepository, only()).existsAuthorByAuthorNameAndNickname(authorDto.getName(), authorDto.getNickname());
        verify(authorRepository, never()).save(any());
    }

    @Test
    void getAuthorBooksTest() {
        author.setBooks(Collections.singleton(book));
        Set<BookDto> expectedBooks = BookMapper.INSTANCE.mapBookDtos(author.getBooks());
        //given
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));

        //when
        Set<BookDto> actualBooks = libraryService.getAuthorBooks(author.getId());

        //then
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getAuthorBooksWithExceptionTest() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.getAuthorBooks(author.getId()));
    }

    @Test
    void createBookTest() {
        book.setAuthorId(author.getId());
        //given
        when(authorRepository.existsAuthorById(author.getId())).thenReturn(true);
        when(bookRepository.save(book)).thenReturn(book);

        //when
        BookDto actual = libraryService.createBook(author.getId(), bookDto);

        //then
        assertEquals(bookDto, actual);
    }

    @Test
    void createBookWithExceptionTest() {
        when(authorRepository.existsAuthorById(author.getId())).thenReturn(false);
        assertThrows(EntityNotFoundException.class,
                () -> libraryService.createBook(author.getId(), bookDto));
        verify(authorRepository, only()).existsAuthorById(author.getId());
        verify(bookRepository, never()).save(any());
    }

    private Library getLibrary() {
        return LibraryMapper.INSTANCE.mapLibrary(getLibraryDto());
    }

    private LibraryDto getLibraryDto() {
        return LibraryDto.builder()
                .name("TEST LIB")
                .address("LVIV")
                .build();
    }

    private Book getBook() {
        return BookMapper.INSTANCE.mapBook(getBookDto());
    }

    private BookDto getBookDto() {
        return BookDto.builder()
                .title("TEST TITLE")
                .description("TEST DESCRIPTION")
                .build();
    }

    private Author getAuthor() {
        return AuthorMapper.INSTANCE.mapAuthor(getAuthorDto());
    }

    private AuthorDto getAuthorDto() {
        return AuthorDto.builder()
                .id(32L)
                .name("Test")
                .nickname("NICKNAME TEST")
                .build();
    }
}
