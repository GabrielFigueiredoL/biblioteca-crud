package com.gabrielfigueiredo.biblioteca.repository;

import com.gabrielfigueiredo.biblioteca.repository.interfaces.ICatalogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CatalogRepository {
    private final Map<String, ICatalogRepository> repositories;
    private final String selectedDB;

    public CatalogRepository(
            Map<String, ICatalogRepository> repositories,
            @Value("${selectedDB}") String selectedDB
    ) {
        this.repositories = repositories;
        this.selectedDB = selectedDB;
    }

    public ICatalogRepository getRepository() {
        ICatalogRepository repository = repositories.get(selectedDB);
        if (repository == null) {
            throw new IllegalArgumentException("Repositório não encontrado: " + selectedDB);
        }
        return repository;
    }
}
