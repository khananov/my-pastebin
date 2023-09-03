package ru.khananov.entyties.dto;

import ru.khananov.entyties.enums.AccessModifier;

public class PasteRequestDto {
    private Long expirationTimeSeconds;
    private String payload;
    private AccessModifier modifier;
}
