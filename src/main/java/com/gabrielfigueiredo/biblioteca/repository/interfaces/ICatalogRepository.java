package com.gabrielfigueiredo.biblioteca.repository.interfaces;

import com.gabrielfigueiredo.biblioteca.domain.CatalogItem;

import java.util.List;

public interface ICatalogRepository {
    void create(CatalogItem catalogItem);
    CatalogItem getById(String id);
    List<CatalogItem> getAll();
    boolean update(CatalogItem catalogItem);
    boolean deleteById(String id);
}
