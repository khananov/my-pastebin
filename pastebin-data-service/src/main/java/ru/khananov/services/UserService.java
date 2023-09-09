package ru.khananov.services;

import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserResponseDto;

public interface UserService {
    UserResponseDto save(User user);

    User getByEmail(String email);
}