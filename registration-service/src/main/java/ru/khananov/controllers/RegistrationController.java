package ru.khananov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khananov.dto.UserRegistrationRequestDto;
import ru.khananov.dto.UserResponseDto;
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
    @Operation(description = "This method is used for registration users")
    public ResponseEntity<UserResponseDto> registration(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        return registrationService.registration(userRegistrationRequestDto);
    }
}