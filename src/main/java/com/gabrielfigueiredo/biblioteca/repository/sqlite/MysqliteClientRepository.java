package com.gabrielfigueiredo.biblioteca.repository.sqlite;

import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.IClientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mysqlite")
public class MysqliteClientRepository implements IClientRepository {
    @Override
    public void create(Client client) {
    }

    @Override
    public Client getById(String id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return List.of();
    }

    @Override
    public Client update(String id, Client client) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean emailExists(String email) {
        return false;
    }
}
