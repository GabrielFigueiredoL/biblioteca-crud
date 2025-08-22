package com.gabrielfigueiredo.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateInventoryDTO(
        @NotBlank(message = "É necessário informar o id do catalogo")
        String catalogId
) {
}
