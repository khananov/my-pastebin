package ru.khananov.exceptions;

public class HashNotFoundException extends RuntimeException {
    public HashNotFoundException(String message) {
        super(message);
    }
}
