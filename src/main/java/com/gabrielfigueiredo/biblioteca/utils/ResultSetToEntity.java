package com.gabrielfigueiredo.biblioteca.utils;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.enums.CatalogIdType;
import com.gabrielfigueiredo.biblioteca.repository.constants.CatalogConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.ClientConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.InventoryConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResultSetToEntity {
    private ResultSetToEntity() {
        throw new UnsupportedOperationException("Classe utilitária - não instanciar");
    }

    public static Client toClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getString(ClientConstants.ID),
                resultSet.getString(ClientConstants.NAME),
                resultSet.getString(ClientConstants.EMAIL),
                resultSet.getString(ClientConstants.PHONE),
                resultSet.getTimestamp(ClientConstants.CREATED_AT),
                resultSet.getTimestamp(ClientConstants.UPDATED_AT)
        );
    }

    public static Catalog toCatalogItem(ResultSet resultSet) throws SQLException {
        return new Catalog(
                resultSet.getString(CatalogConstants.ID),
                CatalogIdType.valueOf(resultSet.getString(CatalogConstants.TYPE)),
                resultSet.getString(CatalogConstants.TYPE_ID),
                resultSet.getString(CatalogConstants.TITLE),
                resultSet.getString(CatalogConstants.AUTHOR),
                resultSet.getString(CatalogConstants.PUBLISHER),
                resultSet.getInt(CatalogConstants.YEAR),
                resultSet.getInt(CatalogConstants.EDITION),
                resultSet.getTimestamp(CatalogConstants.CREATED_AT),
                resultSet.getTimestamp(CatalogConstants.UPDATED_AT)
        );
    }

    public static InventoryResponseDTO toInventoryItem(ResultSet resultSet) throws SQLException {
        Catalog catalog = toCatalogItem(resultSet);

        return new InventoryResponseDTO(
                resultSet.getString(InventoryConstants.ID),
                resultSet.getBoolean(InventoryConstants.IS_AVAILABLE),
                catalog,
                resultSet.getTimestamp(InventoryConstants.CREATED_AT),
                resultSet.getTimestamp(InventoryConstants.UPDATED_AT)
        );
    }
}
