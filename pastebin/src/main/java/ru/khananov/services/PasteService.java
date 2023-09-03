package ru.khananov.services;

import ru.khananov.entyties.dto.PasteRequestDto;
import ru.khananov.entyties.dto.PasteResponseDto;

public interface PasteService {
    PasteResponseDto getByHash(String hash);

    PasteResponseDto create(PasteRequestDto paste);
}
