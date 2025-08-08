package com.gabrielfigueiredo.biblioteca.domain;

import com.gabrielfigueiredo.biblioteca.enums.ItemIdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CatalogItem {
    @Schema(description = "ULID")
    private String id;
    private ItemIdType itemType;
    private String typeId;
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private Integer edition;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
