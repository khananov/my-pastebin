package ru.khananov.services;

import ru.khananov.entities.dto.UserRegistrationRequestDto;

public interface RegistrationService {
    void sendUserRequest(UserRegistrationRequestDto userRegistrationRequestDto);
}
