package com.gabrielfigueiredo.biblioteca.api;

import com.gabrielfigueiredo.biblioteca.domain.CatalogItem;
import com.gabrielfigueiredo.biblioteca.dto.CreateCatalogItemDTO;
import com.gabrielfigueiredo.biblioteca.dto.UpdateCatalogItemDTO;
import com.gabrielfigueiredo.biblioteca.service.CatalogService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/catalog")
@Tag(name = "Catalog Endpoints")
public class CatalogApi {
    @Autowired
    private CatalogService catalogService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid CreateCatalogItemDTO dto) {
        String id = catalogService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<CatalogItem>> getAll() {
        List<CatalogItem> catalogItemList = catalogService.getAll();
        return ResponseEntity.ok().body(catalogItemList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogItem> getById(@PathVariable @Parameter(description = "ULID Type") String id) {
        CatalogItem catalogItem = catalogService.getById(id);
        return ResponseEntity.ok().body(catalogItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogItem> update(@PathVariable @Parameter(description = "ULID Type") String id, @RequestBody @Valid UpdateCatalogItemDTO dto) {
        CatalogItem catalogItem = catalogService.update(id, dto);
        return ResponseEntity.ok().body(catalogItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Parameter(description = "ULID Type") String id) {
        catalogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
