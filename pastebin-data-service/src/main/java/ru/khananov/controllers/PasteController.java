package ru.khananov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.services.PasteService;

@RestController
@RequestMapping("api/data/pastes")
public class PasteController {
    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @GetMapping("/{hash}")
    @Operation(description = "This method is used for auth service communication",
            hidden = true)
    public ResponseEntity<PasteResponseDto> getByHash(@PathVariable String hash,
                                                      @RequestParam String email) {
        PasteResponseDto pasteResponseDto = pasteService.getByHash(hash, email);

        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(pasteResponseDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "This method is used for auth service communication",
            hidden = true)
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto,
                                                 @RequestParam String email) {
        PasteResponseDto pasteResponseDto = pasteService.save(pasteRequestDto, email);

        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(pasteResponseDto, httpHeaders, HttpStatus.CREATED);
    }
}