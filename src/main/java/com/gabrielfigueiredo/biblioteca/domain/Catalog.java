package com.gabrielfigueiredo.biblioteca.domain;

import com.gabrielfigueiredo.biblioteca.dto.catalogDTOs.CreateCatalogDTO;
import com.gabrielfigueiredo.biblioteca.enums.CatalogIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Catalog {
    @Schema(description = "ULID")
    private String id;
    private CatalogIdType catalogType;
    private String catalogTypeId;
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private Integer edition;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Catalog(String id, CreateCatalogDTO dto) {
        this.id = id;
        this.catalogType = dto.itemType();
        this.catalogTypeId = dto.typeId();
        this.title = dto.title();
        this.author = dto.author();
        this.publisher = dto.publisher();
        this.year = dto.year();
        this.edition = dto.edition();
    }
}
