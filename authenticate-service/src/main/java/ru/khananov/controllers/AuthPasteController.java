package ru.khananov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;
import ru.khananov.services.AuthPasteService;

@RestController
@RequestMapping("/api/authentication/paste")
public class AuthPasteController {
    private final AuthPasteService authPasteService;

    @Autowired
    public AuthPasteController(AuthPasteService authPasteService) {
        this.authPasteService = authPasteService;
    }

    @GetMapping("/{hash}")
    @Operation(description = "This method is used for getting paste to auth users")
    public ResponseEntity<PasteResponseDto> getPaste(@PathVariable("hash") String hash,
                                                     HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        PasteResponseDto paste = authPasteService.getPaste(hash, authHeader);

        if (paste == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(paste, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "This method is used for saving paste by auth users")
    public ResponseEntity<PasteResponseDto> save(@RequestBody PasteRequestDto pasteRequestDto,
                                                 HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        PasteResponseDto paste = authPasteService.savePaste(pasteRequestDto, authHeader);

        if (paste == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(paste, HttpStatus.CREATED);
    }
}
