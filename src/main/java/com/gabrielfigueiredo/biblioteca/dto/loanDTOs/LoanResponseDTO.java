package com.gabrielfigueiredo.biblioteca.dto.loanDTOs;

import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;

import java.sql.Timestamp;

public record LoanResponseDTO(
        Timestamp rentDate,
        Timestamp returnDate,
        Timestamp dueDate,
        InventoryResponseDTO inventory,
        Client client,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
