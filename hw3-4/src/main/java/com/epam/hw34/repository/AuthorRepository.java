package com.epam.hw34.repository;

import com.epam.hw34.model.Author;

public interface AuthorRepository {

    Author getById(String authorId);

    Author create(Author author);

}
