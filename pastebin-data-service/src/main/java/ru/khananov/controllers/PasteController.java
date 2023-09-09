package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PasteResponseDto> getByHash(@PathVariable String hash,
                                                      @RequestParam String email) {
        System.out.println(hash + " " + email);
        PasteResponseDto pasteResponseDto = pasteService.getByHash(hash, email);

        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(pasteResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto,
                                                 @RequestParam String email) {
        System.out.println(email);
        PasteResponseDto pasteResponseDto = pasteService.save(pasteRequestDto, email);

        if (pasteResponseDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pasteResponseDto, HttpStatus.CREATED);
    }
}