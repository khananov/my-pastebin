package ru.khananov.data.services;

import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;

public interface AuthPasteService {

    PasteResponseDto getPaste(String hash, String authHeader);

    PasteResponseDto savePaste(PasteRequestDto pasteRequestDto, String authHeader);
}
