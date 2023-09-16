package ru.khananov.mappers;

import org.mapstruct.Mapper;
import ru.khananov.entities.Paste;
import ru.khananov.entities.dto.PasteRequestDto;
import ru.khananov.entities.dto.PasteResponseDto;

@Mapper(componentModel = "spring")
public abstract class PasteMapper {
    public abstract Paste toEntity(PasteRequestDto pasteRequestDto);

    public abstract PasteResponseDto toDto(Paste paste);
}