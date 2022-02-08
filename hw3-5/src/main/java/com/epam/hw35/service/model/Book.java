package com.epam.hw35.service.model;

import com.epam.hw35.controller.dto.Genre;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long authorId;
    private String title;
    private String description;
    private int pages;
    private int publicationYear;
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;
    @ManyToMany(mappedBy = "books")
    private List<Library> libraries = new ArrayList<>();

}
