package com.gabrielfigueiredo.biblioteca.service;

import com.gabrielfigueiredo.biblioteca.domain.CatalogItem;
import com.gabrielfigueiredo.biblioteca.dto.CreateCatalogItemDTO;
import com.gabrielfigueiredo.biblioteca.dto.UpdateCatalogItemDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.IdNotFoundException;
import com.gabrielfigueiredo.biblioteca.repository.CatalogRepository;
import de.huxhorn.sulky.ulid.ULID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public String create(CreateCatalogItemDTO dto) {
        String id = new ULID().nextULID();
        CatalogItem newCatalogItem = new CatalogItem();

        catalogRepository.getRepository().create(newCatalogItem);

        return id;
    }

    public CatalogItem getById(String id) {
        return catalogRepository.getRepository().getById(id);
    }

    public List<CatalogItem> getAll() {
        return catalogRepository.getRepository().getAll();
    }

    public void deleteById(String id) {
        boolean isSuccess = catalogRepository.getRepository().deleteById(id);
        if (!isSuccess) {
            throw new IdNotFoundException("Id Não encontrado");
        }
    }

    public CatalogItem update(String id, UpdateCatalogItemDTO dto) {
        CatalogItem catalogItem = this.getById(id);

        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
        catalogItem.setUpdatedAt(updatedAt);

        if (catalogRepository.getRepository().update(catalogItem)) {
            return catalogItem;
        } else {
            throw new RuntimeException("Erro inesperado");
        }
    }
}
