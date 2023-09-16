package ru.khananov.services;

import ru.khananov.dto.UserRegistrationRequestDto;

public interface RegistrationService {
    void registration(UserRegistrationRequestDto userRegistrationRequestDto);
}