package com.gabrielfigueiredo.biblioteca.infra.exceptions;

public class InventoryNotAvailableException extends RuntimeException {
    public InventoryNotAvailableException(String message) {
        super(message);
    }
}
