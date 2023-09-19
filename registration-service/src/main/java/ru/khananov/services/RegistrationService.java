package ru.khananov.services;

import org.springframework.http.ResponseEntity;
import ru.khananov.dto.UserRegistrationRequestDto;
import ru.khananov.dto.UserResponseDto;

public interface RegistrationService {
    ResponseEntity<UserResponseDto> registration(UserRegistrationRequestDto userRegistrationRequestDto);
}