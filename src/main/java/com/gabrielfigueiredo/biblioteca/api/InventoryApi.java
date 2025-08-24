package com.gabrielfigueiredo.biblioteca.api;

import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.CreateInventoryDTO;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.service.InventoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/inventory")
@Tag(name = "Inventory Endpoints")
public class InventoryApi {
    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateInventoryDTO dto) {
        String id = inventoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAll() {
        List<InventoryResponseDTO> clientList = inventoryService.getAll();
        return ResponseEntity.ok().body(clientList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Parameter(description = "ULID Type") String id) {
        inventoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
