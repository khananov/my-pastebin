package ru.khananov.mappers;

import org.mapstruct.Mapper;
import ru.khananov.entyties.Paste;
import ru.khananov.entyties.dto.PasteRequestDto;
import ru.khananov.entyties.dto.PasteResponseDto;

@Mapper(componentModel = "spring")
public abstract class PasteMapper {
    public abstract Paste toEntity(PasteRequestDto pasteRequestDto);

    public abstract PasteResponseDto toDto(Paste paste);
}
