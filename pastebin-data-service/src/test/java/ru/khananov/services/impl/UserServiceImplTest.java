package ru.khananov.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.exceptions.PasswordsDontMatchException;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserRegistrationRequestDto userRequest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init() {
        userRequest = new UserRegistrationRequestDto("email", "password", "password");
    }

    @Test
    void save_userIsNull_returnsNull() {
        userRequest = null;
        assertNull(userService.save(userRequest));
    }

    @Test
    void save_userAlreadyExists_throwsException() {
        Mockito.when(userRepository.findByEmail(userRequest.getEmail()))
                .thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistException.class, () -> userService.save(userRequest));
    }

    @Test
    void save_passwordsDontMatch_throwsException() {
        userRequest.setRepeatPassword("pass");
        Mockito.when(userRepository.findByEmail(userRequest.getEmail()))
                .thenReturn(Optional.empty());

        assertThrows(PasswordsDontMatchException.class, () -> userService.save(userRequest));
    }

    @Test
    void save_validUser_successSaving() {
        Mockito.when(userMapper.toDto(new User(1L, "email", "password")))
                .thenReturn(new UserResponseDto(1L, "email", "password"));
        Mockito.when(userRepository.save(userMapper.toEntity(userRequest)))
                .thenReturn(new User(1L, "email", "password"));

        UserResponseDto userResponse = userService.save(userRequest);

        assertEquals(userResponse.getEmail(), userRequest.getEmail());
    }

    @Test
    void getByEmail_userNotExist_returnsNull() {
        Mockito.when(userRepository.findByEmail(userRequest.getEmail()))
                .thenReturn(Optional.empty());

        assertNull(userService.getByEmail(userRequest.getEmail()));
    }

    @Test
    void getByEmail_userExist_returnsUser() {
        User user = new User(1L, "email", "password");

        Mockito.when(userRepository.findByEmail(userRequest.getEmail()))
                        .thenReturn(Optional.of(user));
        Mockito.when(userMapper.toDto(user)).thenReturn(new UserResponseDto(
                        user.getId(),
                        user.getEmail(),
                        user.getPassword()));


        UserResponseDto userResponse = userService.getByEmail(userRequest.getEmail());

        assertEquals(userResponse.getEmail(), userRequest.getEmail());
    }
}