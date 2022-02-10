package com.epam.hw36.service.repository;

import com.epam.hw36.service.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsAuthorById(Long id);

    boolean existsAuthorByAuthorNameAndNickname(String name, String nickname);
}
