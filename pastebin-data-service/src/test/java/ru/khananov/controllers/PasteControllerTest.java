package ru.khananov.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.entities.enums.AccessModifier;
import ru.khananov.services.PasteService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasteControllerTest {
    private String hash;
    private String email;
    private PasteResponseDto pasteResponse;
    private PasteRequestDto pasteRequest;

    @Mock
    private PasteService pasteService;

    @InjectMocks
    private PasteController pasteController;

    @BeforeEach
    public void init() {
        hash = "hash";
        email = "email";
        pasteResponse = new PasteResponseDto("payload", hash);
        pasteRequest = new PasteRequestDto(
                10L, "payload", AccessModifier.PUBLIC);
    }

    @Test
    void getByHash_hashNotFound_returnsStatusNoContent() {
        Mockito.when(pasteService.getByHash(hash, email))
                .thenReturn(null);

        ResponseEntity<PasteResponseDto> responseEntity =
                pasteController.getByHash(hash, email);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertNull(responseEntity.getBody());
    }

    @Test
    void getByHash_hashExists_returnsValidResponse() {
        Mockito.when(pasteService.getByHash(hash, email))
                .thenReturn(pasteResponse);

        ResponseEntity<PasteResponseDto> responseEntity =
                pasteController.getByHash(hash, email);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(pasteResponse, responseEntity.getBody());
    }

    @Test
    void save_userAlreadyExists_returnsStatusBadRequest() {
        Mockito.when(pasteService.save(pasteRequest, email))
                .thenReturn(null);

        ResponseEntity<PasteResponseDto> responseEntity =
                pasteController.save(pasteRequest, email);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getHeaders().getContentType());
        assertNull(responseEntity.getBody());
    }

    @Test
    void save_validPaste_successSaving() {
        Mockito.when(pasteService.save(pasteRequest, email))
                .thenReturn(pasteResponse);

        ResponseEntity<PasteResponseDto> responseEntity =
                pasteController.save(pasteRequest, email);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(pasteResponse, responseEntity.getBody());
    }
}