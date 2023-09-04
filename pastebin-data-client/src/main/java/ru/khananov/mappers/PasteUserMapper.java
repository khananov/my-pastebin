package ru.khananov.mappers;

import org.mapstruct.Mapper;
import ru.khananov.entities.PasteUser;
import ru.khananov.entities.dto.PasteUserRequestDto;

@Mapper(componentModel = "spring")
public abstract class PasteUserMapper {
    public abstract PasteUser toEntity(PasteUserRequestDto pasteRequestDto);

    public abstract PasteUserRequestDto toDto(PasteUser pasteUser);
}
