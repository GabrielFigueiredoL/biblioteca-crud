package com.gabrielfigueiredo.biblioteca.utils;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.enums.CatalogIdType;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResultSetToEntity {
    private ResultSetToEntity() {
        throw new UnsupportedOperationException("Classe utilitária - não instanciar");
    }

    public static Client toClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("phone"),
                resultSet.getString("email"),
                resultSet.getTimestamp("created_at"),
                resultSet.getTimestamp("updated_at")
        );
    }

    public static Catalog toCatalogItem(ResultSet resultSet) throws SQLException {
        return new Catalog(
                resultSet.getString("id"),
                CatalogIdType.valueOf(resultSet.getString("catalog_type")),
                resultSet.getString("type_id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("publisher"),
                resultSet.getInt("year"),
                resultSet.getInt("edition"),
                resultSet.getTimestamp("created_at"),
                resultSet.getTimestamp("updated_at")
        );
    }
}
