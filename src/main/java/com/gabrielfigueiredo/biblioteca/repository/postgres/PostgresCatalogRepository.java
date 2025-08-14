package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.Catalog;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.ICatalogRepository;
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

@Repository("postgresCatalog")
public class PostgresCatalogRepository implements ICatalogRepository {
    private final DataSource dataSource;

    public PostgresCatalogRepository(@Qualifier("postgresDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Catalog catalog) {
        String query = """
                INSERT INTO catalog
                (id, catalog_type, type_id, title, author, publisher, year, edition)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, catalog.getId());
            preparedStatement.setString(2, catalog.getCatalogType().toString());
            preparedStatement.setString(3, catalog.getCatalogTypeId());
            preparedStatement.setString(4, catalog.getTitle());
            preparedStatement.setString(5, catalog.getAuthor());
            preparedStatement.setString(6, catalog.getPublisher());
            preparedStatement.setInt(7, catalog.getYear());
            preparedStatement.setInt(8, catalog.getEdition());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    public Optional<Catalog> getById(String id, boolean getByType) {
        String query = "SELECT * FROM catalog WHERE type_id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(ResultSetToEntity.toCatalogItem(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Optional<Catalog> getById(String id) {
        String query = "SELECT * FROM catalog WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(ResultSetToEntity.toCatalogItem(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Catalog> getAll() {
        String query = "SELECT * FROM catalog";
        List<Catalog> catalogList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Catalog catalog = ResultSetToEntity.toCatalogItem(resultSet);
                catalogList.add(catalog);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return catalogList;
    }

    @Override
    public boolean deleteById(String id) {
        String query = "DELETE FROM catalog WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    public boolean deleteById(String id, boolean getByType) {
        String query = "DELETE FROM catalog WHERE type_id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
