package ru.khananov.data.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khananov.data.entities.Paste;
import ru.khananov.data.entities.User;
import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;
import ru.khananov.data.entities.enums.AccessModifier;
import ru.khananov.data.exceptions.HashNotFoundException;
import ru.khananov.data.mappers.PasteMapper;
import ru.khananov.data.repositories.PasteRepository;
import ru.khananov.data.services.PasteService;
import ru.khananov.data.services.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasteServiceImpl implements PasteService {
    private final Logger log = LoggerFactory.getLogger(PasteServiceImpl.class);
    private final PasteRepository pasteRepository;
    private final UserService userService;
    private final PasteMapper pasteMapper;

    @Autowired
    public PasteServiceImpl(PasteRepository pasteRepository,
                            UserService userService,
                            PasteMapper pasteMapper) {
        this.pasteRepository = pasteRepository;
        this.userService = userService;
        this.pasteMapper = pasteMapper;
    }

    @Override
    @Transactional
    public PasteResponseDto getByHash(String hash, String email) {
        Optional<Paste> pasteOptional = pasteRepository.findByHash(hash);

        if (pasteOptional.isEmpty()) {
            log.error(new HashNotFoundException(hash).getMessage());
            return null;
        }

        Paste paste = pasteOptional.get();

        if (checkValidPaste(paste, email))
            return pasteMapper.toDto(paste);

        return null;
    }

    @Override
    @Transactional
    public PasteResponseDto save(PasteRequestDto pasteRequestDto, String email) {
        User user = userService.getByEmail(email);
        Paste paste = pasteMapper.toEntity(pasteRequestDto);
        paste.setUser(user);
        pasteRepository.save(paste);

        paste.setHash(createHash(paste));

        log.info("Paste saved, hash - " + paste.getHash());

        return pasteMapper.toDto(paste);
    }

    private String createHash(Paste paste) {
        return Long.toHexString(
                paste.getId() + paste.getUser().getId() + paste.getCreatedAt().getNano()
        );
    }

    private boolean checkValidPaste(Paste paste, String email) {
        if (checkExpirationTime(paste)) {
            pasteRepository.delete(paste);
            return false;
        }

        if (paste.getModifier().equals(AccessModifier.PUBLIC)) return true;

        return paste.getUser().getEmail().equals(email);
    }

    private boolean checkExpirationTime(Paste paste) {
        return paste.getCreatedAt().plusSeconds(paste.getExpirationTimeSeconds())
                .isBefore(LocalDateTime.now());
    }
}