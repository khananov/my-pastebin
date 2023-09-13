package ru.khananov.data.exceptions;

public class HashNotFoundException extends RuntimeException {
    public HashNotFoundException(String hash) {
        super("Hash - " + hash + " not found");
    }
}