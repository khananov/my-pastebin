package ru.khananov.data.services;

import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;

public interface PasteService {
    PasteResponseDto getByHash(String hash, String email);

    PasteResponseDto save(PasteRequestDto pasteRequestDto, String email);
}