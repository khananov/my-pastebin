package ru.khananov.entities.dto;

import java.util.Arrays;
import java.util.Objects;

public class UserRegistrationRequestDto {
    private String email;
    private char[] password;
    private char[] repeatPassword;

    public UserRegistrationRequestDto() {
    }

    public UserRegistrationRequestDto(String email, char[] password, char[] repeatPassword) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
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

    public char[] getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(char[] repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationRequestDto that = (UserRegistrationRequestDto) o;
        return Objects.equals(email, that.email) && Arrays.equals(password, that.password) && Arrays.equals(repeatPassword, that.repeatPassword);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(email);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(repeatPassword);
        return result;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequestDto{" +
                "email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", repeatPassword=" + Arrays.toString(repeatPassword) +
                '}';
    }
}