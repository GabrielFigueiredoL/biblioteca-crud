package com.gabrielfigueiredo.biblioteca.infra.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
