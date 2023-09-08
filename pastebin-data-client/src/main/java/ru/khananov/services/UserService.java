package ru.khananov.services;

import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;

public interface UserService {

    User getByEmail(String email);

    UserRegistrationRequestDto registration(UserRegistrationRequestDto userRegistrationRequestDto);
}