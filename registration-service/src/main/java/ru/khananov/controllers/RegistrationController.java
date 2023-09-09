package ru.khananov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.services.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping()
    public ResponseEntity<?> registration(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        registrationService.registration(userRegistrationRequestDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}