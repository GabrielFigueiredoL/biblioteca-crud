package com.gabrielfigueiredo.biblioteca.domain;

import com.gabrielfigueiredo.biblioteca.dto.CreateInventoryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Inventory {
    @Schema(description = "ULID")
    private String id;
    private boolean isAvailable;
    @Schema(description = "Catalog ULID")
    private String catalogId;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Inventory (String id, CreateInventoryDTO dto) {
        this.id = id;
        this.isAvailable = true;
        this.catalogId = dto.catalogId();
    }
}
