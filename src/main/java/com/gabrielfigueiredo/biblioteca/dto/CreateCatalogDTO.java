package com.gabrielfigueiredo.biblioteca.dto;

import com.gabrielfigueiredo.biblioteca.enums.CatalogIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCatalogDTO(
        @Schema(description = "ISSN or ISBN")
        CatalogIdType itemType,
        @Schema(description = "ISSN or ISBN number")
        String typeId,
        @NotBlank(message = "É necessário informar o titulo")
        String title,
        String author,
        String publisher,
        Integer year,
        Integer edition
) {
}
