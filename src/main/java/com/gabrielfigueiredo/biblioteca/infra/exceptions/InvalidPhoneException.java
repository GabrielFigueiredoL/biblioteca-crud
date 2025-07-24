package com.gabrielfigueiredo.biblioteca.infra.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }
}
