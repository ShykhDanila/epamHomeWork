package com.epam.hw34.service.mapper;

import com.epam.hw34.controller.dto.AuthorDto;
import com.epam.hw34.service.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto mapAuthorDto(Author author);

    Author mapAuthor(AuthorDto authorDto);
}
