package com.gabrielfigueiredo.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Create Client Body")
public record CreateClientDTO(
        @NotBlank(message = "É necessário informar o nome")
        String name,
        @NotBlank(message = "É necessário informar o telefone")
        String phone,
        @NotBlank(message = "É necessário informar o email")
        @Schema(description = "Valid Email")
        @Email(message = "É necessário que seja um email válido")
        String email
) {
}
