package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.entities.dto.PasteUserRequestDto;
import ru.khananov.services.PasteUserService;

@RestController
@RequestMapping("/api/registration")
public class PasteUserController {
    private final PasteUserService pasteUserService;

    @Autowired
    public PasteUserController(PasteUserService pasteUserService) {
        this.pasteUserService = pasteUserService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody PasteUserRequestDto pasteUserRequestDto) {
        if (pasteUserService.registration(pasteUserRequestDto) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
