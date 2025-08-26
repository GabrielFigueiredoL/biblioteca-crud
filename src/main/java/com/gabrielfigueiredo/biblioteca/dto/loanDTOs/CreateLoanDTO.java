package com.gabrielfigueiredo.biblioteca.dto.loanDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public record CreateLoanDTO(
        @NotNull
        Timestamp rentDate,
        @NotNull
        Timestamp dueDate,

        @NotBlank
        @Size(min = 26, max = 26)
        String clientId,
        @NotBlank
        @Size(min = 26, max = 26)
        String inventoryId
) {
}
