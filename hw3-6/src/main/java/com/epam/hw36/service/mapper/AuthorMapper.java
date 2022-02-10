package com.epam.hw36.service.mapper;

import com.epam.hw36.controller.dto.AuthorDto;
import com.epam.hw36.service.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "name", source = "authorName")
    AuthorDto mapAuthorDto(Author author);

    @Mapping(target = "authorName", source = "name")
    Author mapAuthor(AuthorDto authorDto);
}
