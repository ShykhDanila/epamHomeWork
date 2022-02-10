package com.epam.hw36.controller;

import com.epam.hw36.controller.dto.AuthorDto;
import com.epam.hw36.controller.dto.BookDto;
import com.epam.hw36.controller.dto.LibraryDto;
import com.epam.hw36.service.LibraryService;
import com.epam.hw36.service.exception.AuthorAlreadyExistsException;
import com.epam.hw36.service.exception.EntityNotFoundException;
import com.epam.hw36.service.exception.LibraryAlreadyExistsException;
import com.epam.hw36.test.config.TestWebConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static java.lang.String.format;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = LibraryController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    LibraryService libraryService;

    private final LibraryDto libraryDto = getLibraryDto();
    private final AuthorDto authorDto = getAuthorDto();
    private final BookDto bookDto = getBookDto();

    @Test
    void createLibraryTest() throws Exception {
        when(libraryService.createLibrary(libraryDto)).thenReturn(libraryDto);

        mockMvc.perform(post("/library/create")
                .content(objectMapper.writeValueAsString(libraryDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address").value(libraryDto.getAddress()))
                .andExpect(jsonPath("$.name").value(libraryDto.getName()));
    }

    @Test
    void createUser_expectException_onError_LibraryAlreadyExistsException() throws Exception {
        String message = "library already exists";
        when(libraryService.createLibrary(libraryDto)).thenThrow(new LibraryAlreadyExistsException(message));

        mockMvc.perform(post("/library/create")
                .content(objectMapper.writeValueAsString(libraryDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void getAllLibrariesTest() throws Exception {
        when(libraryService.getAllLibraries()).thenReturn(Collections.singletonList(libraryDto));

        mockMvc.perform(get("/library"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].address").value(libraryDto.getAddress()))
                .andExpect(jsonPath("$[0].name").value(libraryDto.getName()));
    }

    @Test
    void getAllLibraries_expectException_onError_NullPointerException() throws Exception {
        String message = "error message";
        when(libraryService.getAllLibraries()).thenThrow(new NullPointerException(message));

        mockMvc.perform(get("/library"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(message));
    }

    @Test
    void getLibraryTest() throws Exception {
        when(libraryService.getLibrary(libraryDto.getAddress())).thenReturn(libraryDto);

        mockMvc.perform(get("/library/" + libraryDto.getAddress()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").value(libraryDto.getAddress()))
                .andExpect(jsonPath("$.name").value(libraryDto.getName()));
    }

    @Test
    void getLibrary_expectException_onError_EntityNotFoundException() throws Exception {
        String message = "entity not found message";
        when(libraryService.getLibrary(libraryDto.getAddress())).thenThrow(new EntityNotFoundException(message));

        mockMvc.perform(get("/library/" + libraryDto.getAddress()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void deleteLibraryTest() throws Exception {
        doNothing().when(libraryService).deleteLibrary(libraryDto.getAddress());

        mockMvc.perform(delete("/library/" + libraryDto.getAddress()))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(libraryService, only()).deleteLibrary(libraryDto.getAddress());
    }

    @Test
    void addBookTest() throws Exception {
        String bookTitle = "TestLibrary";
        when(libraryService.addBook(libraryDto.getName(), bookTitle)).thenReturn(libraryDto);

        mockMvc.perform(post("/library/" + libraryDto.getName() + "/book/" + bookTitle))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.address").value(libraryDto.getAddress()))
                .andExpect(jsonPath("$.name").value(libraryDto.getName()));
    }

    @Test
    void getLibraryByBookTitleTest() throws Exception {
        String bookTitle = "TestLibrary";
        when(libraryService.getLibraryByBookTitle(bookTitle)).thenReturn(Collections.singleton(libraryDto));

        mockMvc.perform(get("/library/book/" + bookTitle))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].address").value(libraryDto.getAddress()))
                .andExpect(jsonPath("$[0].name").value(libraryDto.getName()));
    }

    @Test
    void getAuthorByIdTest() throws Exception {
        when(libraryService.getAuthorInfo(authorDto.getId())).thenReturn(authorDto);

        mockMvc.perform(get("/library/author/" + authorDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(authorDto.getId()))
                .andExpect(jsonPath("$.name").value(authorDto.getName()))
                .andExpect(jsonPath("$.nickname").value(authorDto.getNickname()));
    }

    @Test
    void getAuthor_expectException_onError_EntityNotFoundException() throws Exception {
        String message = format("Author with id %s is not found", authorDto.getId());
        when(libraryService.getAuthorInfo(authorDto.getId())).thenThrow(new EntityNotFoundException(message));

        mockMvc.perform(get("/library/author/" + authorDto.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void createAuthorTest() throws Exception {
        AuthorDto authorDtoWithIdNull = authorDto;
        authorDtoWithIdNull.setId(null);

        when(libraryService.createAuthor(authorDtoWithIdNull)).thenReturn(authorDto);

        mockMvc.perform(post("/library/author")
                .content(objectMapper.writeValueAsString(authorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(authorDto.getId()))
                .andExpect(jsonPath("$.name").value(authorDto.getName()))
                .andExpect(jsonPath("$.nickname").value(authorDto.getNickname()));
    }

    @Test
    void createAuthor_expectException_onError_AuthorAlreadyExistsException() throws Exception {
        String message = format("Author with name %s  and nickname %s exists", authorDto.getId(), authorDto.getNickname());

        AuthorDto authorDtoWithIdNull = authorDto;
        authorDtoWithIdNull.setId(null);

        when(libraryService.createAuthor(authorDtoWithIdNull)).thenThrow(new AuthorAlreadyExistsException(message));

        mockMvc.perform(post("/library/author")
                .content(objectMapper.writeValueAsString(authorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void getAuthorBooksTest() throws Exception {
        when(libraryService.getAuthorBooks(authorDto.getId())).thenReturn(Collections.singleton(bookDto));

        mockMvc.perform(get("/library/author/" + authorDto.getId() + "/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value(bookDto.getTitle()))
                .andExpect(jsonPath("$[0].description").value(bookDto.getDescription()))
                .andExpect(jsonPath("$[0].pages").value(bookDto.getPages()));
    }

    @Test
    void createBookTest() throws Exception {
        when(libraryService.createBook(authorDto.getId(), bookDto)).thenReturn(bookDto);

        mockMvc.perform(post("/library/author/" + authorDto.getId() + "/book")
                .content(objectMapper.writeValueAsString(bookDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(bookDto.getTitle()))
                .andExpect(jsonPath("$.description").value(bookDto.getDescription()))
                .andExpect(jsonPath("$.pages").value(bookDto.getPages()));
    }

    @Test
    void createBook_expectException_onError_EntityNotFoundException() throws Exception {
        String message = "Author exists";
        when(libraryService.createBook(authorDto.getId(), bookDto)).thenThrow(new EntityNotFoundException(message));

        mockMvc.perform(post("/library/author/" + authorDto.getId() + "/book")
                .content(objectMapper.writeValueAsString(bookDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));
    }


    private LibraryDto getLibraryDto() {
        return LibraryDto.builder()
                .address("Lviv")
                .name("TEST")
                .build();
    }

    private AuthorDto getAuthorDto() {
        return AuthorDto.builder()
                .id(456L)
                .name("Test")
                .nickname("TEST NICKNANE")
                .build();
    }

    private BookDto getBookDto() {
        return BookDto.builder()
                .title("test book")
                .description("test description")
                .pages(110)
                .build();
    }
}
