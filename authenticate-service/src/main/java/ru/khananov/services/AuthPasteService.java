package ru.khananov.services;

import ru.khananov.dto.PasteRequestDto;
import ru.khananov.dto.PasteResponseDto;

public interface AuthPasteService {

    PasteResponseDto getPaste(String hash, String authHeader);

    PasteResponseDto savePaste(PasteRequestDto pasteRequestDto, String authHeader);
}