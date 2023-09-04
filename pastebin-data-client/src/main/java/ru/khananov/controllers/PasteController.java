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
    public ResponseEntity<PasteResponseDto> getByHash(@PathVariable String hash) {
        return new ResponseEntity<>(pasteService.getByHash(hash), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto) {
        return new ResponseEntity<>(pasteService.create(pasteRequestDto), HttpStatus.OK);
    }
}
