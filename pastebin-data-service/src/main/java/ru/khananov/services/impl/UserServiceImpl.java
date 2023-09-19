package ru.khananov.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.exceptions.PasswordsDontMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.exceptions.UserNotFoundException;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.UserRepository;
import ru.khananov.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto save(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRegistrationRequestDto == null) return null;

        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            log.error(new UserAlreadyExistException(userRegistrationRequestDto.getEmail()).getMessage());
            throw new UserAlreadyExistException(userRegistrationRequestDto.getEmail());
        }

        encodePassword(userRegistrationRequestDto);
        User user = userMapper.toEntity(userRegistrationRequestDto);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseGet(() -> {
            log.error(new UserNotFoundException(email).getMessage());
            return null;
        }));
    }

    private void encodePassword(UserRegistrationRequestDto user) {
        if (checkPasswordsMatching(user)) {
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );

            user.setRepeatPassword(
                    passwordEncoder.encode(user.getRepeatPassword())
            );
        }
    }

    private boolean checkPasswordsMatching(UserRegistrationRequestDto user) {
        if (!user.getPassword().equals(user.getRepeatPassword()))
            throw new PasswordsDontMatchException("Passwords doesn't match");

        log.info("Passwords match");
        return true;
    }
}