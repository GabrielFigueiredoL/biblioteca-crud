package com.gabrielfigueiredo.biblioteca.utils;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
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

    public static InventoryResponseDTO toInventoryItem(ResultSet resultSet) throws SQLException {
        Catalog catalog = new Catalog(
                resultSet.getString("catalog_id"),
                CatalogIdType.valueOf(resultSet.getString("catalog_type")),
                resultSet.getString("type_id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("publisher"),
                resultSet.getInt("year"),
                resultSet.getInt("edition"),
                resultSet.getTimestamp("catalog_created_at"),
                resultSet.getTimestamp("catalog_updated_at")
        );

        return new InventoryResponseDTO(
                resultSet.getString("inventory_id"),
                resultSet.getBoolean("is_available"),
                catalog,
                resultSet.getTimestamp("inventory_created_at"),
                resultSet.getTimestamp("inventory_updated_at")
        );
    }
}
