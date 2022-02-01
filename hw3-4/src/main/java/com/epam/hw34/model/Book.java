package com.epam.hw34.model;

import com.epam.hw34.dto.Genre;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class Book {

    private String id;
    private String authorId;
    private String title;
    private String description;
    private int pages;
    private int publicationYear;
    private Set<Genre> genres;
    private List<Library> libraries = new ArrayList<>();

}
