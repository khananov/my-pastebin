package ru.khananov.exceptions;

public class HashNotFoundException extends RuntimeException {
    public HashNotFoundException(String hash) {
        super("Hash - " + hash + " not found");
    }
}
