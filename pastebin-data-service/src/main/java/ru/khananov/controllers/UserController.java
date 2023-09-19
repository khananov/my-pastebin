package ru.khananov.controllers;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entities.dto.UserRegistrationRequestDto;
import ru.khananov.entities.dto.UserResponseDto;
import ru.khananov.services.UserService;

@RestController
@RequestMapping("/api/data/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDto> getByEmail(@RequestParam("email") String email) {
        UserResponseDto userResponseDto = userService.getByEmail(email);

        if (userResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(userResponseDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "This method is used for registration service communication",
            hidden = true)
    public ResponseEntity<UserResponseDto> save(
            @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        UserResponseDto userResponseDto = userService.save(userRegistrationRequestDto);

        if (userResponseDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(userResponseDto, httpHeaders, HttpStatus.OK);
    }
}