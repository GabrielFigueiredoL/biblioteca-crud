package com.gabrielfigueiredo.biblioteca.infra.exceptions;

public class InvalidCatalogException extends RuntimeException {
    public InvalidCatalogException(String message) {
        super(message);
    }
}
