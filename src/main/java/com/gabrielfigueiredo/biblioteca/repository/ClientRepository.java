package com.gabrielfigueiredo.biblioteca.repository;

import com.gabrielfigueiredo.biblioteca.repository.interfaces.IClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClientRepository {
    private final Map<String, IClientRepository> repositories;
    private final String selectedDB;

    public ClientRepository(
            Map<String, IClientRepository> repositories,
            @Value("${selectedDB}") String selectedDB
    ) {
        this.repositories = repositories;
        this.selectedDB = selectedDB;
    }

    public IClientRepository getRepository() {
        String key = selectedDB + "Client";
        IClientRepository repository = repositories.get(key);
        if (repository == null) {
            throw new IllegalArgumentException("Repositório não encontrado: " + key);
        }
        return repository;
    }
}
