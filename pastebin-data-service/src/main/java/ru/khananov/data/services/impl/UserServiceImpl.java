package ru.khananov.data.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.khananov.data.entities.User;
import ru.khananov.data.entities.dto.UserResponseDto;
import ru.khananov.data.exceptions.UserNotFoundException;
import ru.khananov.data.mappers.UserMapper;
import ru.khananov.data.repositories.UserRepository;
import ru.khananov.data.services.UserService;

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