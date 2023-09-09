package ru.khananov.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class PasteServiceImpl implements PasteService {
    private final Logger log = LoggerFactory.getLogger(PasteServiceImpl.class);
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
                .orElseGet(() -> {
                    log.error(new HashNotFoundException(hash).getMessage());
                    return null;
                }));
    }

    @Override
    @Transactional
    public PasteResponseDto save(PasteRequestDto pasteRequestDto) {
        Paste paste = pasteRepository.save(pasteMapper.toEntity(pasteRequestDto));
        paste.setHash(createHash(paste));

        log.info("Paste saved, hash - " + paste.getHash());

        return pasteMapper.toDto(paste);
    }

    private String createHash(Paste paste) {
        return Long.toHexString(
                paste.getId() + paste.getUser().getId() + paste.getCreatedAt().getNano()
        );
    }
}