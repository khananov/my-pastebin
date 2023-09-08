package ru.khananov.entities.dto;

import java.util.Arrays;
import java.util.Objects;

public class UserAuthRequestDto {
    private String email;
    private char[] password;

    public UserAuthRequestDto() {
    }

    public UserAuthRequestDto(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthRequestDto that = (UserAuthRequestDto) o;
        return Objects.equals(email, that.email) && Arrays.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(email);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "UserAuthRequestDto{" +
                "email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
