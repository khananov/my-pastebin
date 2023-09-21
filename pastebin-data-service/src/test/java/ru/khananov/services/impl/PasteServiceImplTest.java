package ru.khananov.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.khananov.entities.Paste;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.entities.enums.AccessModifier;
import ru.khananov.mappers.PasteMapper;
import ru.khananov.mappers.UserMapper;
import ru.khananov.repositories.PasteRepository;
import ru.khananov.services.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasteServiceImplTest {
    private String hash;
    private String email;
    private PasteResponseDto pasteResponse;
    private PasteRequestDto pasteRequest;
    private UserResponseDto userResponse;
    private Paste paste;

    @Mock
    private PasteRepository pasteRepository;

    @Mock
    private UserService userService;

    @Mock
    private PasteMapper pasteMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private PasteServiceImpl pasteService;

    @BeforeEach
    public void init() {
        hash = "hash";
        email = "email";
        pasteResponse = new PasteResponseDto("payload", hash);
        pasteRequest = new PasteRequestDto(
                10L, "payload", AccessModifier.PUBLIC);
        paste = new Paste(
                1L,
                LocalDateTime.now(),
                90L,
                "payload",
                AccessModifier.PUBLIC,
                hash,
                new User(1L, email, "pass"));
        userResponse = new UserResponseDto(1L, email, "pass");
    }

    @Test
    void getByHash_pasteNotFound_returnsNull() {
        Mockito.when(pasteRepository.findByHash(hash))
                .thenReturn(Optional.empty());

        assertNull(pasteService.getByHash(hash, email));
    }

    @Test
    void getByHash_pasteExpirationInvalid_returnsNull() {
        paste.setExpirationTimeSeconds(0L);

        Mockito.when(pasteRepository.findByHash(hash)).thenReturn(Optional.of(paste));

        assertNull(pasteService.getByHash(hash, email));
    }

    @Test
    void getByHash_pasteEmailsDontMatch_returnsNull() {
        paste.setModifier(AccessModifier.PRIVATE);
        Mockito.when(pasteRepository.findByHash(hash)).thenReturn(Optional.of(paste));

        assertNull(pasteService.getByHash(hash, "123"));
    }

    @Test
    void getByHash_pasteValid_returnsPasteDto() {
        Mockito.when(pasteRepository.findByHash(hash)).thenReturn(Optional.of(paste));
        Mockito.when(pasteMapper.toDto(paste)).thenReturn(pasteResponse);

        assertNotNull(pasteService.getByHash(hash, email));
    }

    @Test
    void save_userNotFound_returnsNull() {
        Mockito.when(userService.getByEmail(email)).thenReturn(null);

        assertNull(pasteService.save(pasteRequest, email));
    }

    @Test
    void save_validPaste_successSaving() {
        Mockito.when(userService.getByEmail(email)).thenReturn(userResponse);
        Mockito.when(pasteMapper.toEntity(pasteRequest)).thenReturn(paste);
        Mockito.when(pasteMapper.toDto(paste)).thenReturn(pasteResponse);
        Mockito.when(userMapper.toEntity(userResponse))
                .thenReturn(new User(1L, email, "pass"));

        assertNotNull(pasteService.save(pasteRequest, email));
    }
}