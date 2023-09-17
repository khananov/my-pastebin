package ru.khananov.services;

import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;

public interface UserService {
    UserResponseDto save(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto getByEmail(String email);
}