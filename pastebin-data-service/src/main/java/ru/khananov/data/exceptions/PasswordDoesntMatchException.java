package ru.khananov.data.exceptions;

public class PasswordDoesntMatchException extends RuntimeException {
    public PasswordDoesntMatchException(String message) {
        super(message);
    }
}