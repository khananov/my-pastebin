package ru.khananov.services;

import ru.khananov.entities.dto.UserAuthResponseDto;
import ru.khananov.entities.dto.UserRegistrationRequestDto;

public interface RegistrationService {
    UserAuthResponseDto sendUserRequest(UserRegistrationRequestDto userRegistrationRequestDto);
}