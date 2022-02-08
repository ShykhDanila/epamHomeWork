package com.epam.hw35.service.mapper;

import com.epam.hw35.controller.dto.UserDto;
import com.epam.hw35.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserDto(User user);

    User mapUser(UserDto userDto);

    List<UserDto> mapPageUserDto(List<User> users);
}
