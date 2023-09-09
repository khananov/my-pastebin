package ru.khananov.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("Email - " + email + " already exist");
    }
}