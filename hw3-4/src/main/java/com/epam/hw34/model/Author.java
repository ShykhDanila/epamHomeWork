package com.epam.hw34.model;

import lombok.Data;

import java.util.Set;

@Data
public class Author {

    private String id;
    private String name;
    private String nickname;
    private Set<Book> books;

}
