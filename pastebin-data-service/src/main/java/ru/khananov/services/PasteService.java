package ru.khananov.services;

import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;

public interface PasteService {
    PasteResponseDto getByHash(String hash, String email);

    PasteResponseDto save(PasteRequestDto pasteRequestDto, String email);
}