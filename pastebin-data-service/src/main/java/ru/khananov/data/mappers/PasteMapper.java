package ru.khananov.data.mappers;

import org.mapstruct.Mapper;
import ru.khananov.data.entities.Paste;
import ru.khananov.data.entities.dto.PasteRequestDto;
import ru.khananov.data.entities.dto.PasteResponseDto;

@Mapper(componentModel = "spring")
public abstract class PasteMapper {
    public abstract Paste toEntity(PasteRequestDto pasteRequestDto);

    public abstract PasteResponseDto toDto(Paste paste);
}