package ru.khananov.dto;

import java.util.Objects;

public class UserAuthResponseDto {
    private String token;

    public UserAuthResponseDto() {
    }

    public UserAuthResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthResponseDto that = (UserAuthResponseDto) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "UserAuthResponseDto{" +
                "token='" + token + '\'' +
                '}';
    }
}