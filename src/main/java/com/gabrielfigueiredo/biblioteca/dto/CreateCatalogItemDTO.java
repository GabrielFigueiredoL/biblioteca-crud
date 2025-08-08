package com.gabrielfigueiredo.biblioteca.dto;

import com.gabrielfigueiredo.biblioteca.enums.ItemIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record CreateCatalogItemDTO(
        @NotNull(message = "É necessário informar o tipo do item")
        @Schema(description = "ISSN or ISBN")
        ItemIdType itemType,
        @NotBlank(message = "É necessário informar o identificador")
        @Schema(description = "ISSN or ISBN number")
        String typeId,
        @NotBlank(message = "É necessário informar o titulo")
        String title,
        String author,
        String publisher,
        Integer year,
        Integer edition,

        Timestamp createdAt,
        Timestamp updatedAt
) {
}
