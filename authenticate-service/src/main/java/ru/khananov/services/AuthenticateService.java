package ru.khananov.services;

import ru.khananov.entities.dto.UserAuthRequestDto;
import ru.khananov.entities.dto.UserAuthResponseDto;

public interface AuthenticateService {
    UserAuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto);
}