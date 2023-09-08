package ru.khananov.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.exceptions.PasswordDoesntMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.exceptions.UserNotFoundException;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.UserRepository;
import ru.khananov.services.UserService;

@Service
public class UserServiceImpl implements UserService {
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
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public UserRegistrationRequestDto registration(UserRegistrationRequestDto userRegistrationRequestDto) {
        encodePassword(userRegistrationRequestDto);

        User user = userMapper.toEntity(userRegistrationRequestDto);

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(user.getEmail());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    private void encodePassword(UserRegistrationRequestDto user) {
        if (checkPasswordMatching(user)) {
            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );

            user.setRepeatPassword(
                    passwordEncoder.encode(user.getRepeatPassword())
            );
        }
    }

    private boolean checkPasswordMatching(UserRegistrationRequestDto user) {
        if (!user.getPassword().equals(user.getRepeatPassword()))
            throw new PasswordDoesntMatchException("Password doesn't match");

        return true;
    }
}