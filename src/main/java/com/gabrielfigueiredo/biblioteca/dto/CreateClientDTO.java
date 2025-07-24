package com.gabrielfigueiredo.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank(message = "É necessário informar o nome")
        String name,
        @NotBlank(message = "É necessário informar o telefone")
        String phone,
        @NotBlank(message = "É necessário informar o email")
        @Email(message = "É necessário que seja um email válido")
        String email
) {
}
