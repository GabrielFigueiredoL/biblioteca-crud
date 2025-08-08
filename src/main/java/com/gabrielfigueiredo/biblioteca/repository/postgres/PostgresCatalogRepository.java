package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.CatalogItem;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.ICatalogRepository;

import java.util.List;

public class PostgresCatalogRepository implements ICatalogRepository {
    @Override
    public void create(CatalogItem catalogItem) {

    }

    @Override
    public CatalogItem getById(String id) {
        return null;
    }

    @Override
    public List<CatalogItem> getAll() {
        return List.of();
    }

    @Override
    public boolean update(CatalogItem catalogItem) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
