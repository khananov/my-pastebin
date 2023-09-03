package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.entyties.dto.PasteRequestDto;
import ru.khananov.entyties.dto.PasteResponseDto;
import ru.khananov.services.PasteService;

@RestController
@RequestMapping("api/pastebin")
public class PasteController {
    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto) {
        return new ResponseEntity<>(pasteService.create(pasteRequestDto), HttpStatus.OK);
    }
}
