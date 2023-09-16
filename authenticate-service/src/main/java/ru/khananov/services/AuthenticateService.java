package ru.khananov.services;

import ru.khananov.dto.UserAuthRequestDto;
import ru.khananov.dto.UserAuthResponseDto;

public interface AuthenticateService {
    UserAuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto);
}