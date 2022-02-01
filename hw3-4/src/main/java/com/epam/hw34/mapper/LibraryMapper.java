package com.epam.hw34.mapper;

import com.epam.hw34.dto.LibraryDto;
import com.epam.hw34.model.Library;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface LibraryMapper {
    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

    LibraryDto mapLibraryDto(Library library);

    Library mapLibrary(LibraryDto libraryDto);

    Set<LibraryDto> mapLibraryDtos(Set<Library> libraries);
}
