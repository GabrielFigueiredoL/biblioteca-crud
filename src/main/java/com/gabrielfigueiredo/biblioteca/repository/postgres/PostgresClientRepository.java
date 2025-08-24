package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.Client;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.IClientRepository;
import com.gabrielfigueiredo.biblioteca.utils.ResultSetToEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("postgresClient")
public class PostgresClientRepository implements IClientRepository {
    private final DataSource dataSource;

    public PostgresClientRepository(@Qualifier("postgresDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Client client) {
        String query = """
                INSERT INTO clients
                (id, name, email, phone)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    public Optional<Client> getById(String id) {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(ResultSetToEntity.toClient(resultSet));
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        String query = "SELECT * FROM clients";
        List<Client> clientList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = ResultSetToEntity.toClient(resultSet);
                clientList.add(client);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return clientList;
    }

    @Override
    public boolean update(Client client) {
        String query = """
                    UPDATE clients
                    SET name = ?,
                    email = ?,
                    phone = ?,
                    updated_at = ?
                    WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setTimestamp(4, client.getUpdatedAt());
            preparedStatement.setString(5, client.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public boolean emailExists(String email) {
        String query = "SELECT email FROM clients WHERE email = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
