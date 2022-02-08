package com.epam.hw35.service.impl;

import com.epam.hw35.controller.dto.UserDto;
import com.epam.hw35.service.exception.EntityNotFoundException;
import com.epam.hw35.service.exception.UserAlreadyExistsException;
import com.epam.hw35.service.mapper.UserMapper;
import com.epam.hw35.service.model.Library;
import com.epam.hw35.service.model.User;
import com.epam.hw35.service.repository.LibraryRepository;
import com.epam.hw35.service.repository.UserRepository;
import com.epam.hw35.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LibraryRepository libraryRepository;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(format("User with email %s is not found", email)));
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        Page<User> allUsers = userRepository.findAll(PageRequest.of(0, 12, Sort.by("firstName").and(Sort.by("lastName"))));
        return UserMapper.INSTANCE.mapPageUserDto(allUsers.getContent());
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto, String password) {
        String email = userDto.getEmail();
        log.info("createUser with email {}", email);
        if (userRepository.existsUserByEmail(email)) {
            throw new UserAlreadyExistsException(format("User with email %s exists", email));
        }
        User user = UserMapper.INSTANCE.mapUser(userDto);
        user.setPassword(password);
        user.setWrittenOn(Instant.now());
        user = userRepository.save(user);
        log.info("User with email {} successfully created", email);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("Updating user with email {}", email);
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(format("User with email %s is not found", email)));
        user = populateUserWithPresentUserDtoFields(user, userDto);
        userRepository.save(user);
        log.info("User with email {} successfully updated", user.getEmail());
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    @Transactional
    public void addLibrary(String email, String libraryName) {
        log.info("User with email {} add library with name {}", email, libraryName);
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(format("User with email %s is not found", email)));
        Library library = libraryRepository.getLibraryByLibraryName(libraryName).orElseThrow(() ->
                new EntityNotFoundException(format("Library with name %s is not found", libraryName)));

        user.getLibraries().add(library);
        library.getUsers().add(user);

        userRepository.save(user);
        libraryRepository.save(library);
        log.info("User with email {} successfully added library with name {}", email, libraryName);
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(format("User with email %s is not found", email)));
        userRepository.delete(user);
        log.info("User with email {} successfully deleted", email);
    }

    private User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        if (Objects.nonNull(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (Objects.nonNull(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (Objects.nonNull(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        return user;
    }
}