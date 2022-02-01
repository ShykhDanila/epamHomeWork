package com.epam.hw34.mapper;

import com.epam.hw34.dto.AuthorDto;
import com.epam.hw34.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto mapAuthorDto(Author author);

    Author mapAuthor(AuthorDto authorDto);
}
