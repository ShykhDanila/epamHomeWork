package com.epam.hw34.service.repository;

import com.epam.hw34.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getByTitle(String bookTitle);
}

