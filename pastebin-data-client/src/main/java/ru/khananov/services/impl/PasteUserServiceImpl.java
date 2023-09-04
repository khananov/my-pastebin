package ru.khananov.services.impl;

import org.springframework.stereotype.Service;
import ru.khananov.entities.PasteUser;
import ru.khananov.entities.dto.PasteUserRequestDto;
import ru.khananov.exceptions.UserAlreadyExistException;
import ru.khananov.mappers.PasteUserMapper;
import ru.khananov.repositories.PasteUserRepository;
import ru.khananov.services.PasteUserService;

@Service
public class PasteUserServiceImpl implements PasteUserService {
    private final PasteUserRepository pasteUserRepository;
    private final PasteUserMapper pasteUserMapper;

    public PasteUserServiceImpl(PasteUserRepository pasteUserRepository, PasteUserMapper pasteUserMapper) {
        this.pasteUserRepository = pasteUserRepository;
        this.pasteUserMapper = pasteUserMapper;
    }

    @Override
    public PasteUserRequestDto registration(PasteUserRequestDto pasteUserRequestDto) {
        PasteUser pasteUser =  pasteUserMapper.toEntity(pasteUserRequestDto);

        if (pasteUserRepository.findByEmail(pasteUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(pasteUser.getEmail());
        }

        return pasteUserMapper.toDto(pasteUserRepository.save(pasteUser));
    }
}
