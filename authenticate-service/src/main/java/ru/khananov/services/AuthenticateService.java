package ru.khananov.services;

import ru.khananov.data.entities.dto.UserAuthRequestDto;
import ru.khananov.data.entities.dto.UserAuthResponseDto;

public interface AuthenticateService {
    UserAuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto);
}