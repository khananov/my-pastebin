package ru.khananov.services;

import ru.khananov.entities.dto.PasteUserRequestDto;

public interface RegistrationService {
    void sendUserRequest(PasteUserRequestDto pasteUserRequestDto);
}
