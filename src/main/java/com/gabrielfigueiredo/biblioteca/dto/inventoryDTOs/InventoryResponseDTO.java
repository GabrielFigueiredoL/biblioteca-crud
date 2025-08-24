package com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;

import java.sql.Timestamp;

public record InventoryResponseDTO(
        String id,
        boolean isAvailable,
        Catalog catalog,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
