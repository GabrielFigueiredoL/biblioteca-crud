package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.domain.Inventory;
import com.gabrielfigueiredo.biblioteca.dto.InventoryResponseDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
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
        String query = """
                INSERT INTO inventory
                (id, is_available, catalog_id)
                VALUES (?, ?, ?)
                """;

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
        String query =  """
                SELECT
                    i.id AS inventory_id,
                    i.created_at AS inventory_created_at,
                    i.updated_at AS inventory_updated_at,
                    i.is_available,
                    i.catalog_id AS inventory_catalog_id,
                    c.id AS catalog_id,
                    c.created_at AS catalog_created_at,
                    c.updated_at AS catalog_updated_at,
                    c.catalog_type,
                    c.type_id,
                    c.title,
                    c.author,
                    c.publisher,
                    c.year,
                    c.edition
                FROM inventory i
                JOIN catalog c ON i.catalog_id = c.id;
    """;
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
    public Optional<Inventory> getById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        String query = "DELETE FROM inventory WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
