package ru.khananov.data.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.data.entities.dto.UserAuthRequestDto;
import ru.khananov.data.entities.dto.UserAuthResponseDto;
import ru.khananov.data.services.AuthenticateService;

@RestController
@RequestMapping("/api/authentication/login")
public class AuthController {
    private final AuthenticateService authenticateService;

    @Autowired
    public AuthController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping()
    @Operation(description = "This method is used for authenticate users")
    public ResponseEntity<UserAuthResponseDto> login(
            @RequestBody UserAuthRequestDto userAuthRequestDto) {
        return ResponseEntity.ok(authenticateService.authenticate(userAuthRequestDto));
    }
}