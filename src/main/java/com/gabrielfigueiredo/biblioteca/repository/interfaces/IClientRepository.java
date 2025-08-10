package com.gabrielfigueiredo.biblioteca.repository.interfaces;

import com.gabrielfigueiredo.biblioteca.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    void create(Client client);
    Optional<Client> getById(String id);
    List<Client> getAll();
    boolean update(Client client);
    boolean deleteById(String id);

    boolean emailExists(String email);
}
