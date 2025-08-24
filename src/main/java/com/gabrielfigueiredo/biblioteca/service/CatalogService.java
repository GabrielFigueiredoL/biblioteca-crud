package com.gabrielfigueiredo.biblioteca.service;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.dto.catalogDTOs.CreateCatalogDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.IdNotFoundException;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.InvalidCatalogException;
import com.gabrielfigueiredo.biblioteca.repository.CatalogRepository;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public String create(CreateCatalogDTO dto) {
        String id = new ULID().nextULID();
        boolean catalogExists = catalogRepository.getRepository().getById(dto.typeId(), true).isPresent();
        if (catalogExists) {
            throw new InvalidCatalogException("Item já existe no catalogo.");
        }

        Catalog newCatalog = new Catalog(id, dto);

        catalogRepository.getRepository().create(newCatalog);

        return id;
    }

    public Catalog getById(String id, boolean getByType) {
        Optional<Catalog> catalog = getByType ? catalogRepository.getRepository().getById(id, true) : catalogRepository.getRepository().getById(id);

        if (catalog.isEmpty()) {
            throw new IdNotFoundException("Item não encontrado");
        }

        return catalog.get();
    }

    public List<Catalog> getAll() {
        return catalogRepository.getRepository().getAll();
    }

    public void deleteById(String id, boolean getByType) {
        boolean isSuccess = getByType ? catalogRepository.getRepository().deleteById(id, true) : catalogRepository.getRepository().deleteById(id);

        if (!isSuccess) {
            throw new IdNotFoundException("Id Não encontrado");
        }
    }
 }
