package com.gabrielfigueiredo.biblioteca.infra.exceptions;

import com.gabrielfigueiredo.biblioteca.dto.ErrorBodyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Void> handleIdNotFound(IdNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ErrorBodyDTO> handleInvalidPhone(InvalidPhoneException exception) {
        return ResponseEntity.unprocessableEntity().body(new ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorBodyDTO> handleIllegalState(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorBodyDTO> handleInvalidEmail(InvalidEmailException exception) {
        return ResponseEntity.unprocessableEntity().body(new ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorBodyDTO> handleSQLError(SQLException exception) {
        return ResponseEntity.unprocessableEntity().body(new ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(InvalidCatalogException.class)
    public ResponseEntity<ErrorBodyDTO> handleInvalidCatalog(InvalidCatalogException exception) {
        return ResponseEntity.unprocessableEntity().body(new ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(InventoryNotAvailableException.class)
    public ResponseEntity<ErrorBodyDTO> handleInventoryNotAvailable(InventoryNotAvailableException exception) {
        return ResponseEntity.unprocessableEntity().body(new  ErrorBodyDTO(exception.getMessage()));
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorBodyDTO> handleInventoryNotAvailable(InvalidDateException exception) {
        return ResponseEntity.unprocessableEntity().body(new  ErrorBodyDTO(exception.getMessage()));
    }
}