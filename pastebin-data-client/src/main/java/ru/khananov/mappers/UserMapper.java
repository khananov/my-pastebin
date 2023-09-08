package ru.khananov.mappers;

import org.mapstruct.Mapper;
import ru.khananov.entities.User;
import ru.khananov.entities.dto.UserRegistrationRequestDto;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User toEntity(UserRegistrationRequestDto pasteRequestDto);

    public abstract UserRegistrationRequestDto toDto(User user);
}