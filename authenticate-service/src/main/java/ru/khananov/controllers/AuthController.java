package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.entities.dto.UserAuthRequestDto;
import ru.khananov.entities.dto.UserAuthResponseDto;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.services.AuthenticateService;
import ru.khananov.services.RegistrationService;

@RestController
@RequestMapping("/secure/v1/paste")
public class AuthController {
    private final RegistrationService registrationService;
    private final AuthenticateService authenticateService;

    @Autowired
    public AuthController(RegistrationService registrationService,
                          AuthenticateService authenticateService) {
        this.registrationService = registrationService;
        this.authenticateService = authenticateService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserAuthResponseDto> registration(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        return ResponseEntity.ok(registrationService.sendUserRequest(userRegistrationRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponseDto> login(
            @RequestBody UserAuthRequestDto userAuthRequestDto) {
        return ResponseEntity.ok(authenticateService.authenticate(userAuthRequestDto));
    }
}