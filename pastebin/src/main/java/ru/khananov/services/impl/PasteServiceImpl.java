package ru.khananov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khananov.entyties.Paste;
import ru.khananov.entyties.dto.PasteRequestDto;
import ru.khananov.entyties.dto.PasteResponseDto;
import ru.khananov.repositories.PasteRepository;
import ru.khananov.services.PasteService;

@Service
public class PasteServiceImpl implements PasteService {
    private final PasteRepository pasteRepository;

    @Autowired
    public PasteServiceImpl(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    @Override
    public PasteResponseDto getByHash(String hash) {
        return null;
    }

    @Override
    public PasteResponseDto create(PasteRequestDto paste) {
        return null;
    }
}
