package com.gabrielfigueiredo.biblioteca.repository.postgres;

import com.gabrielfigueiredo.biblioteca.domain.Loan;

import com.gabrielfigueiredo.biblioteca.dto.loanDTOs.LoanResponseDTO;
import com.gabrielfigueiredo.biblioteca.infra.exceptions.DatabaseException;
import com.gabrielfigueiredo.biblioteca.repository.constants.CatalogConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.ClientConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.InventoryConstants;
import com.gabrielfigueiredo.biblioteca.repository.constants.LoanConstants;
import com.gabrielfigueiredo.biblioteca.repository.interfaces.ILoanRepository;
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

@Repository("postgresLoan")
public class PostgresLoanRepository implements ILoanRepository {
    private final DataSource dataSource;

    public PostgresLoanRepository(@Qualifier("postgresDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Loan loan) {
        String query = String.format("""
                INSERT INTO %s
                (id, rent_date, due_date, client_id, inventory_id)
                VALUES (?, ?, ?, ?, ?)
                """, LoanConstants.TABLE_NAME);

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, loan.getId());
            preparedStatement.setTimestamp(2, loan.getRentDate());
            preparedStatement.setTimestamp(3, loan.getDueDate());
            preparedStatement.setString(4, loan.getClientId());
            preparedStatement.setString(5, loan.getInventoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    @Override
    public Optional<Loan> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<LoanResponseDTO> getAll() {
            String query = String.format("""
        SELECT
            l.rent_date AS %s,
            l.return_date AS %s,
            l.due_date AS %s,
            l.created_at AS %s,
            l.updated_at AS %s,

            i.id AS %s,
            i.is_available AS %s,
            i.catalog_id AS %s,
            i.created_at AS %s,
            i.updated_at AS %s,

            c.id AS %s,
            c.catalog_type AS %s,
            c.type_id AS %s,
            c.title AS %s,
            c.author AS %s,
            c.publisher AS %s,
            c.year AS %s,
            c.edition AS %s,
            c.created_at AS %s,
            c.updated_at AS %s,

            cl.id AS %s,
            cl.name AS %s,
            cl.email AS %s,
            cl.phone AS %s,
            cl.created_at AS %s,
            cl.updated_at AS %s

        FROM %s l
        JOIN %s i  ON l.inventory_id = i.id
        JOIN %s c  ON i.catalog_id = c.id
        JOIN %s cl ON l.client_id = cl.id;
        """,
                    LoanConstants.RENT_DATE,
                    LoanConstants.RETURN_DATE,
                    LoanConstants.DUE_DATE,
                    LoanConstants.CREATED_AT,
                    LoanConstants.UPDATED_AT,
                    InventoryConstants.ID,
                    InventoryConstants.IS_AVAILABLE,
                    InventoryConstants.CATALOG_ID,
                    InventoryConstants.CREATED_AT,
                    InventoryConstants.UPDATED_AT,
                    CatalogConstants.ID,
                    CatalogConstants.TYPE,
                    CatalogConstants.TYPE_ID,
                    CatalogConstants.TITLE,
                    CatalogConstants.AUTHOR,
                    CatalogConstants.PUBLISHER,
                    CatalogConstants.YEAR,
                    CatalogConstants.EDITION,
                    CatalogConstants.CREATED_AT,
                    CatalogConstants.UPDATED_AT,
                    ClientConstants.ID,
                    ClientConstants.NAME,
                    ClientConstants.EMAIL,
                    ClientConstants.PHONE,
                    ClientConstants.CREATED_AT,
                    ClientConstants.UPDATED_AT,
                    LoanConstants.TABLE_NAME,
                    InventoryConstants.TABLE_NAME,
                    CatalogConstants.TABLE_NAME,
                    ClientConstants.TABLE_NAME
            );

        List<LoanResponseDTO> loanList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LoanResponseDTO inventory = ResultSetToEntity.toLoanItem(resultSet);
                loanList.add(inventory);
            }
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return loanList;
    }

    @Override
    public boolean update(Loan loan) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public Optional<Loan> returnLoan(String id) {
        return null;
    }

    @Override
    public List<Loan> getAllOverdue() {
        return List.of();
    }
}
