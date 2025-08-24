package com.gabrielfigueiredo.biblioteca.dto.clientDTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@Schema(name = "Update Client Body")
public record UpdateClientDTO(
        String name,
        String phone,
        @Schema(description = "Valid email")
        @Email(message = "É necessário que seja um email válido")
        String email
) {
}
