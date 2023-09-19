package ru.khananov.services.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.khananov.clients.UserFeignClient;
import ru.khananov.dto.UserRegistrationRequestDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    private UserRegistrationRequestDto user;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private UserFeignClient userFeignClient;

    @BeforeEach
    public void setUp() {
        user = new UserRegistrationRequestDto("email", "password", "password");
    }

    @Test
    void registration_successRegistration() {
        registrationService.registration(user);
        Mockito.verify(userFeignClient, Mockito.times(1)).save(user);
    }
}