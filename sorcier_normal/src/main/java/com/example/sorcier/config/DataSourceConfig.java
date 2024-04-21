package com.example.sorcier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration pour les sources de données utilisées dans l'application.
 */
@Configuration
public class DataSourceConfig {

    // URL de la base de données injectée depuis les propriétés de l'application.
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    // Nom d'utilisateur pour la base de données, injecté depuis les propriétés de l'application.
    @Value("${spring.datasource.username}")
    private String username;

    // Mot de passe pour la base de données, injecté depuis les propriétés de l'application.
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Crée un bean DataSource pour SQL Server.
     * Ce bean est seulement créé pour les profils 'sqlserver'.
     *
     * @return DataSource configurée pour SQL Server.
     */
    @Bean
    @Profile("sqlserver")
    public DataSource sqlServerDataSource() {
        // Crée une nouvelle instance de DriverManagerDataSource.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Configure l'URL de la base de données.
        dataSource.setUrl(databaseUrl);
        // Configure le nom d'utilisateur de la base de données.
        dataSource.setUsername(username);
        // Configure le mot de passe de la base de données.
        dataSource.setPassword(password);
        // Retourne la source de données configurée.
        return dataSource;
    }
}
