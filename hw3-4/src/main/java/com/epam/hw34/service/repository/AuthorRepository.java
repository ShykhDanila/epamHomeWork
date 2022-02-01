package com.epam.hw34.service.repository;

import com.epam.hw34.service.model.Author;

public interface AuthorRepository {

    Author getById(String authorId);

    Author create(Author author);

}
