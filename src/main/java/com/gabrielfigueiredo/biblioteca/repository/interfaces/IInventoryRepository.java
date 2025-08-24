package com.gabrielfigueiredo.biblioteca.repository.interfaces;

import com.gabrielfigueiredo.biblioteca.domain.Inventory;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IInventoryRepository {
    void create(Inventory inventory);
    List<InventoryResponseDTO> getAll();
    Optional<Inventory> getById(String id, boolean getByType);
    Optional<InventoryResponseDTO> getById(String id);
    boolean deleteById(String id);
}
