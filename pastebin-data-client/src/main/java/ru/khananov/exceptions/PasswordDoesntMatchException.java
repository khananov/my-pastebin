package ru.khananov.exceptions;

public class PasswordDoesntMatchException extends RuntimeException {
    public PasswordDoesntMatchException(String message) {
        super(message);
    }
}