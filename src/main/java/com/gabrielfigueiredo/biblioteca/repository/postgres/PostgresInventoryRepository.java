package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.Inventory;
import com.gabrielfigueiredo.biblioteca.dto.inventoryDTOs.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
import com.gabrielfigueiredo.biblioteca.repository.constants.CatalogConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.InventoryConstants;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.IInventoryRepository;
import com.gabrielfigueiredo.biblioteca.utils.ResultSetToEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("postgresInventory")
public class PostgresInventoryRepository implements IInventoryRepository {
    private final DataSource dataSource;

    public PostgresInventoryRepository(@Qualifier("postgresDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Inventory inventory) {
        String query = String.format("""
                INSERT INTO %s
                (id, is_available, catalog_id)
                VALUES (?, ?, ?)
                """, InventoryConstants.TABLE_NAME);

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inventory.getId());
            preparedStatement.setBoolean(2, inventory.isAvailable());
            preparedStatement.setString(3, inventory.getCatalogId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    public List<InventoryResponseDTO> getAll() {
        String query = String.format("""
            SELECT
                i.id AS %s,
                i.is_available AS %s,
                i.catalog_id AS %s,
                i.created_at AS %s,
                i.updated_at AS %s,
                c.id AS %s,
                c.created_at AS %s,
                c.updated_at AS %s,
                c.catalog_type AS %s,
                c.type_id AS %s,
                c.title AS %s,
                c.author AS %s,
                c.publisher AS %s,
                c.year AS %s,
                c.edition AS %s
            FROM %s i
            JOIN %s c ON i.catalog_id = c.id;
            """,
                InventoryConstants.ID,
                InventoryConstants.IS_AVAILABLE,
                InventoryConstants.CATALOG_ID,
                InventoryConstants.CREATED_AT,
                InventoryConstants.UPDATED_AT,
                CatalogConstants.ID,
                CatalogConstants.CREATED_AT,
                CatalogConstants.UPDATED_AT,
                CatalogConstants.TYPE,
                CatalogConstants.TYPE_ID,
                CatalogConstants.TITLE,
                CatalogConstants.AUTHOR,
                CatalogConstants.PUBLISHER,
                CatalogConstants.YEAR,
                CatalogConstants.EDITION,
                InventoryConstants.TABLE_NAME,
                CatalogConstants.TABLE_NAME
        );
        List<InventoryResponseDTO> inventoryList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InventoryResponseDTO inventory = ResultSetToEntity.toInventoryItem(resultSet);
                inventoryList.add(inventory);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return inventoryList;
    }

    @Override
    public Optional<Inventory> getById(String id, boolean getByType) {
        return Optional.empty();
    }

    @Override
    public Optional<InventoryResponseDTO> getById(String id) {
        String query = String.format("""
            SELECT
                i.id AS %s,
                i.is_available AS %s,
                i.catalog_id AS %s,
                i.created_at AS %s,
                i.updated_at AS %s,
                c.id AS %s,
                c.created_at AS %s,
                c.updated_at AS %s,
                c.catalog_type AS %s,
                c.type_id AS %s,
                c.title AS %s,
                c.author AS %s,
                c.publisher AS %s,
                c.year AS %s,
                c.edition AS %s
            FROM %s i
            JOIN %s c ON i.catalog_id = c.id
            WHERE i.id = ?;
            """,
                InventoryConstants.ID,
                InventoryConstants.IS_AVAILABLE,
                InventoryConstants.CATALOG_ID,
                InventoryConstants.CREATED_AT,
                InventoryConstants.UPDATED_AT,
                CatalogConstants.ID,
                CatalogConstants.CREATED_AT,
                CatalogConstants.UPDATED_AT,
                CatalogConstants.TYPE,
                CatalogConstants.TYPE_ID,
                CatalogConstants.TITLE,
                CatalogConstants.AUTHOR,
                CatalogConstants.PUBLISHER,
                CatalogConstants.YEAR,
                CatalogConstants.EDITION,
                InventoryConstants.TABLE_NAME,
                CatalogConstants.TABLE_NAME
        );
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(ResultSetToEntity.toInventoryItem(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        String query = String.format("""
            DELETE FROM %s WHERE id = ?
        """, InventoryConstants.TABLE_NAME);
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
