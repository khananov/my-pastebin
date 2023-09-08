package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.services.PasteService;

@RestController
@RequestMapping("api/data")
public class PasteController {
    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @GetMapping("/{hash}")
    public ResponseEntity<?> getByHash(@PathVariable String hash) {
        PasteResponseDto pasteResponseDto = pasteService.getByHash(hash);
        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(pasteResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PasteRequestDto pasteRequestDto) {
        PasteResponseDto pasteResponseDto = pasteService.create(pasteRequestDto);
        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(pasteResponseDto, HttpStatus.CREATED);
    }
}