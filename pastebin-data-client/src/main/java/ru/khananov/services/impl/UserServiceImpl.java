package ru.khananov.services.impl;

import org.springframework.stereotype.Service;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.exceptions.PasswordDoesntMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.exceptions.UserNotFoundException;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.UserRepository;
import ru.khananov.services.UserService;

import java.util.Arrays;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public UserRegistrationRequestDto registration(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (!checkPasswordMatching(
                userRegistrationRequestDto.getPassword(),
                userRegistrationRequestDto.getRepeatPassword()))
            throw new PasswordDoesntMatchException("Password doesn't match");

        User user =  userMapper.toEntity(userRegistrationRequestDto);

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistException(user.getEmail());

        return userMapper.toDto(userRepository.save(user));
    }

    private boolean checkPasswordMatching(char[] password, char[] confirmPassword) {
        return Arrays.toString(password).equals(Arrays.toString(confirmPassword));
    }
}
