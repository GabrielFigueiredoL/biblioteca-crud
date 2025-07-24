package com.gabrielfigueiredo.biblioteca.dto;

import jakarta.validation.constraints.Email;

public record UpdateClientDTO(
        String name,
        String phone,
        @Email(message = "É necessário que seja um email válido")
        String email
) {
}
