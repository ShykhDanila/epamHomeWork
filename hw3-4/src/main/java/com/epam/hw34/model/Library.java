package com.epam.hw34.model;

import lombok.Data;

import java.util.List;

@Data
public class Library {

    private String id;
    private String name;
    private String adress;
    private List<Book> books;
}
