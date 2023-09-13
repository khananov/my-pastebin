package ru.khananov.data.mappers;

import org.mapstruct.Mapper;
import ru.khananov.data.entities.User;
import ru.khananov.data.entities.dto.UserRegistrationRequestDto;
import ru.khananov.data.entities.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User toEntity(UserRegistrationRequestDto pasteRequestDto);

    public abstract UserResponseDto toDto(User user);
}