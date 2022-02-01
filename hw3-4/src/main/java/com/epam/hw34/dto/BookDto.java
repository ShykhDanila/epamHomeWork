package com.epam.hw34.dto;

import com.epam.hw34.model.Author;
import lombok.Data;

import java.util.Set;

@Data
public class BookDto {

    public String id;
    public String title;
    public String description;
    public int pages;
    public int publicationYear;
    public Set<Genre> genres;
}
