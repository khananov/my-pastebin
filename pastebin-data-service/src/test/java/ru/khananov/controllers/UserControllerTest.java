package ru.khananov.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private UserResponseDto userResponse;
    private UserRegistrationRequestDto userRequest;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void init() {
        userResponse = new UserResponseDto(1L, "email", "password");
        userRequest = new UserRegistrationRequestDto("email", "password", "password");
    }

    @Test
    void getByEmail_userNotFound_returnsStatusNoContent() {
        String email = "email";
        Mockito.when(userService.getByEmail(email))
                .thenReturn(null);

        ResponseEntity<UserResponseDto> responseEntity = userController.getByEmail(email);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertNull(responseEntity.getBody());
    }

    @Test
    void getByEmail_userExists_returnsValidResponse() {
        Mockito.when(userService.getByEmail(userResponse.getEmail()))
                .thenReturn(userResponse);

        ResponseEntity<UserResponseDto> responseEntity =
                userController.getByEmail(userResponse.getEmail());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    void save_userAlreadyExists_returnsStatusBadRequest() {
        Mockito.when(userService.save(userRequest))
                .thenReturn(null);

        ResponseEntity<UserResponseDto> responseEntity =
                userController.save(userRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertNull(responseEntity.getBody());
    }

    @Test
    void save_validUser_successSaving() {
        Mockito.when(userService.save(userRequest))
                .thenReturn(userResponse);

        ResponseEntity<UserResponseDto> responseEntity =
                userController.save(userRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(userResponse, responseEntity.getBody());
    }
}