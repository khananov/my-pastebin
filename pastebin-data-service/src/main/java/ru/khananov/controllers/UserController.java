package ru.khananov.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khananov.entities.User;
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

    @GetMapping
    public ResponseEntity<User> getByEmail(@RequestParam("email") String email) {
        User userResponseDto = userService.getByEmail(email);

        if (userResponseDto == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "This method is used for registration service communication",
            hidden = true)
    public ResponseEntity<UserResponseDto> save(@RequestBody User user) {
        UserResponseDto userResponseDto = userService.save(user);
        if (userResponseDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}