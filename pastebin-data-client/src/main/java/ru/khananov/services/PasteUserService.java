package ru.khananov.services;

import ru.khananov.entities.dto.PasteUserRequestDto;

public interface PasteUserService {
    PasteUserRequestDto registration(PasteUserRequestDto pasteUserRequestDto);
}
