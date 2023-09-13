package ru.khananov.data.services;

import ru.khananov.data.entities.User;
import ru.khananov.data.entities.dto.UserResponseDto;

public interface UserService {
    UserResponseDto save(User user);

    User getByEmail(String email);
}