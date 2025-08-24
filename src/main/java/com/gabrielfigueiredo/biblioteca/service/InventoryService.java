package com.gabrielfigueiredo.biblioteca.service;

import com.gabrielfigueiredo.biblioteca.domain.Inventory;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.CreateInventoryDTO;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.IdNotFoundException;
import com.gabrielfigueiredo.biblioteca.repository.InventoryRepository;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final CatalogService catalogService;

    public InventoryService(InventoryRepository inventoryRepository, CatalogService catalogService) {
        this.inventoryRepository = inventoryRepository;
        this.catalogService = catalogService;
    }

    public String create(CreateInventoryDTO dto) {
        String id = new ULID().nextULID();
        if (catalogService.getById(dto.catalogId(), false) == null) {
            throw new IdNotFoundException("Item de catalogo não encontrado.");
        }

        Inventory inventory = new Inventory(id, dto);

        inventoryRepository.getRepository().create(inventory);

        return id;
    }

    public List<InventoryResponseDTO> getAll() {
        return inventoryRepository.getRepository().getAll();
    }

    public void deleteById(String id) {
        boolean isSuccess = inventoryRepository.getRepository().deleteById(id);

        if (!isSuccess) {
            throw new IdNotFoundException("Id não encontrado");
        }
    }

    public InventoryResponseDTO getById(String id) {
        if (inventoryRepository.getRepository().getById(id).isEmpty()) {
            throw new IdNotFoundException("Id não encontrado");
        }
        return inventoryRepository.getRepository().getById(id).get();
    }
}
