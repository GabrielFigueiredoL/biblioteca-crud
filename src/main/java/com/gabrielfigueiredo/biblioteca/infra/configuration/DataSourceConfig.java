package com.gabrielfigueiredo.biblioteca.infra.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    @ConditionalOnProperty(name = "selectedDB", havingValue = "postgres")
    public DataSource postgresDataSource() {
        System.out.println("Datasource Postgres");
        return DataSourceBuilder.create().build();
    }
}
