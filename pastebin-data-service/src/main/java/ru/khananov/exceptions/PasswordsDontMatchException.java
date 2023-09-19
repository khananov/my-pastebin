package ru.khananov.exceptions;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(String message) {
        super(message);
    }
}