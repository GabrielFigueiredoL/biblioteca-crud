package com.gabrielfigueiredo.biblioteca.repository;

import com.gabrielfigueiredo.biblioteca.repository.interfaces.IInventoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryRepository {
    private final Map<String, IInventoryRepository> repositories;
    private final String selectedDB;

    public InventoryRepository(
            Map<String, IInventoryRepository> repositories,
            @Value("${selectedDB}") String selectedDB
    ) {
        this.repositories = repositories;
        this.selectedDB = selectedDB;
    }

    public IInventoryRepository getRepository() {
        String key = selectedDB + "Inventory";
        IInventoryRepository repository = repositories.get(key);
        if (repository == null) {
            throw new IllegalArgumentException("Repositório não encontrado: " + key);
        }
        return repository;
    }
}
