package ru.khananov.entities.dto;

import java.util.Objects;

public class PasteResponseDto {
    private String payload;
    private String hash;

    public PasteResponseDto() {
    }

    public PasteResponseDto(String payload, String hash) {
        this.payload = payload;
        this.hash = hash;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasteResponseDto that = (PasteResponseDto) o;
        return Objects.equals(payload, that.payload) && Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload, hash);
    }

    @Override
    public String toString() {
        return "PasteResponseDto{" +
                "payload='" + payload + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
