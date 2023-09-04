package ru.khananov.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khananov.entities.Paste;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.exceptions.HashNotFoundException;
import ru.khananov.mappers.PasteMapper;
import ru.khananov.repositories.PasteRepository;
import ru.khananov.services.PasteService;

import java.util.Base64;

@Service
public class PasteServiceImpl implements PasteService {
    private final PasteRepository pasteRepository;
    private final PasteMapper pasteMapper;

    @Autowired
    public PasteServiceImpl(PasteRepository pasteRepository, PasteMapper pasteMapper) {
        this.pasteRepository = pasteRepository;
        this.pasteMapper = pasteMapper;
    }

    @Override
    public PasteResponseDto getByHash(String hash) {
        return pasteMapper.toDto(pasteRepository.findByHash(hash)
                .orElseThrow(() -> new HashNotFoundException(hash)));
    }

    @Override
    @Transactional
    public PasteResponseDto create(PasteRequestDto pasteRequestDto) {
        Paste paste = pasteRepository.save(pasteMapper.toEntity(pasteRequestDto));
        paste.setHash(createHash(paste));

        return pasteMapper.toDto(paste);
    }

    private String createHash(Paste paste) {
        String uniqueValue = paste.getCreated_at() + paste.getPayload() + paste.getId();
        return Base64.getEncoder().encodeToString(uniqueValue.getBytes());
    }
}
