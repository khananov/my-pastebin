package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entyties.Paste;
import ru.khananov.entyties.dto.PasteRequestDto;
import ru.khananov.entyties.dto.PasteResponseDto;
import ru.khananov.mappers.PasteMapper;
import ru.khananov.services.PasteService;

@RestController
@RequestMapping("api/pastebin")
public class PasteController {
    private final PasteService pasteService;
    private final PasteMapper pasteMapper;

    @Autowired
    public PasteController(PasteService pasteService, PasteMapper pasteMapper) {
        this.pasteService = pasteService;
        this.pasteMapper = pasteMapper;
    }

    @PostMapping
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto) {
        Paste paste = pasteMapper.toEntity(pasteRequestDto);
        paste.setHash("hash");
        System.out.println(paste);
        return new ResponseEntity<>(pasteMapper.toDto(paste), HttpStatus.OK);
    }
}
