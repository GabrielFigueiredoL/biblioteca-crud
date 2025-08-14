package com.gabrielfigueiredo.biblioteca.repository.interfaces;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;

import java.util.List;
import java.util.Optional;

public interface ICatalogRepository {
    void create(Catalog catalog);
    Optional<Catalog> getById(String id, boolean getByType);
    Optional<Catalog> getById(String id);
    List<Catalog> getAll();
    boolean deleteById(String id);
    boolean deleteById(String id, boolean getByType);
}
