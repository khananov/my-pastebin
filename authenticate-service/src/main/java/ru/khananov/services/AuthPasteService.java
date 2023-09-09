package ru.khananov.services;

import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;

public interface AuthPasteService {

    PasteResponseDto getPaste(String hash, String authHeader);

    PasteResponseDto savePaste(PasteRequestDto pasteRequestDto, String authHeader);
}
