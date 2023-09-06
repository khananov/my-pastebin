package ru.khananov.services;

import ru.khananov.entities.PasteUser;
import ru.khananov.entities.dto.PasteUserRequestDto;

public interface PasteUserService {

    PasteUser getByEmail(String email);

    PasteUserRequestDto registration(PasteUserRequestDto pasteUserRequestDto);
}