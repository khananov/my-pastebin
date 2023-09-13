package ru.khananov.data.entities.dto;

import java.util.Objects;

public class UserRegistrationRequestDto {
    private String email;
    private String password;
    private String repeatPassword;

    public UserRegistrationRequestDto() {
    }

    public UserRegistrationRequestDto(String email, String password, String repeatPassword) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationRequestDto that = (UserRegistrationRequestDto) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(repeatPassword, that.repeatPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, repeatPassword);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}