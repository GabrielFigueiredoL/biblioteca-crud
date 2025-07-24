package com.gabrielfigueiredo.biblioteca.infra.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class FlywayConfig {
    @Bean
    @ConditionalOnProperty(name = "selectedDB", havingValue = "postgres")
    public Flyway flywayPostgres(@Qualifier("postgresDataSource")DataSource postgresDataSource) {
        System.out.println("Executando postgres");
        try (Connection connection = postgresDataSource.getConnection()) {
            if (connection == null || connection.isClosed()) {
                throw new IllegalStateException("Falha ao conectar ao banco de dados PostgreSQL.");
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Erro ao validar conexão com o PostgreSQL", exception);
        }
        Flyway flyway = Flyway.configure()
                .dataSource(postgresDataSource)
                .locations("classpath:db/migration/postgres")
                .load();

        flyway.migrate();

        return flyway;
    }
}
