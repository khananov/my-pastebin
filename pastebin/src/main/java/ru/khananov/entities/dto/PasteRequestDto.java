package ru.khananov.entities.dto;

import ru.khananov.entities.enums.AccessModifier;

import java.util.Objects;

public class PasteRequestDto {
    private Long expirationTimeSeconds;
    private String payload;
    private AccessModifier modifier;

    public PasteRequestDto() {
    }

    public PasteRequestDto(Long expirationTimeSeconds, String payload, AccessModifier modifier) {
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.payload = payload;
        this.modifier = modifier;
    }

    public Long getExpirationTimeSeconds() {
        return expirationTimeSeconds;
    }

    public void setExpirationTimeSeconds(Long expirationTimeSeconds) {
        this.expirationTimeSeconds = expirationTimeSeconds;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public AccessModifier getModifier() {
        return modifier;
    }

    public void setModifier(AccessModifier modifier) {
        this.modifier = modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasteRequestDto that = (PasteRequestDto) o;
        return Objects.equals(expirationTimeSeconds, that.expirationTimeSeconds) && Objects.equals(payload, that.payload) && modifier == that.modifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationTimeSeconds, payload, modifier);
    }

    @Override
    public String toString() {
        return "PasteRequestDto{" +
                "expirationTimeSeconds=" + expirationTimeSeconds +
                ", payload='" + payload + '\'' +
                ", modifier=" + modifier +
                '}';
    }
}
