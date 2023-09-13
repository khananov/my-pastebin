package ru.khananov.data.services;

import ru.khananov.data.entities.dto.UserRegistrationRequestDto;

public interface RegistrationService {
    void registration(UserRegistrationRequestDto userRegistrationRequestDto);
}