package ru.khananov.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.exceptions.HashNotFoundException;
import ru.khananov.exceptions.PasswordDoesntMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.exceptions.UserNotFoundException;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.UserRepository;
import ru.khananov.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto save(User user) {
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            log.error(new UserNotFoundException(email).getMessage());
            return null;
        });
    }
}