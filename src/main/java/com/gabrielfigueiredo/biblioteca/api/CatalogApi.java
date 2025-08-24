package com.gabrielfigueiredo.biblioteca.api;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.dto.catalogDTOs.CreateCatalogDTO;
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
    public ResponseEntity<String> create(@RequestBody @Valid CreateCatalogDTO dto) {
        String id = catalogService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping
    public ResponseEntity<List<Catalog>> getAll() {
        List<Catalog> catalogList = catalogService.getAll();
        return ResponseEntity.ok().body(catalogList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getById(@PathVariable @Parameter(description = "ULID Type or (ISBN or ISSN) if byType is true") String id, @RequestParam(defaultValue = "false") boolean byType) {
        Catalog catalog = catalogService.getById(id, byType);
        return ResponseEntity.ok().body(catalog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Parameter(description = "ULID Type or (ISBN or ISSN) if byType is true\")") String id, @RequestParam(defaultValue = "false") boolean byType) {
        catalogService.deleteById(id, byType);
        return ResponseEntity.noContent().build();
    }
}
