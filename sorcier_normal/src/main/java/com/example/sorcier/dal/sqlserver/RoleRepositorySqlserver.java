package com.example.sorcier.dal.sqlserver;

// Importations nécessaires pour les configurations de profil.
import org.springframework.context.annotation.Profile;

// Importation des classes JDBC pour l'exécution des requêtes.
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

// Importation de l'annotation Repository pour définir cette classe comme un composant de la couche de données.
import org.springframework.stereotype.Repository;

import com.example.sorcier.dal.RoleRepository;

/**
 * Implémentation SQL Server du repository pour les rôles.
 */
@Repository
@Profile("sqlserver") // Spécifie que ce repository est activé sous le profil SQL Server.
public class RoleRepositorySqlserver implements RoleRepository {
	
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    /**
     * Enregistre un nouveau rôle associé à un utilisateur.
     *
     * @param id_utilisateur - L'ID de l'utilisateur auquel le rôle est assigné.
     * @param role - Le rôle à attribuer.
     */
    public void save(int id_utilisateur, String role) {
    	
        // Définit la requête SQL pour insérer un nouveau rôle.
        String sql = "INSERT INTO Roles (id_utilisateur, role) VALUES (:id_utilisateur, :role);";
        
        // Prépare les paramètres pour la requête.
        MapSqlParameterSource source = new MapSqlParameterSource();
        
        // Ajoute l'ID de l'utilisateur.
        source.addValue("id_utilisateur", id_utilisateur);
        
        // Ajoute le rôle.
        source.addValue("role", role); 
        
        // Exécute de la mise à jour pour insérer le nouveau rôle.
        namedParameterJdbcTemplate.update(sql, source);
    }
    
    /**
     * Supprime les rôles associés à un utilisateur.
     *
     * @param id_utilisateur - L'ID de l'utilisateur dont les rôles doivent être supprimés.
     */
    public void remove(int id_utilisateur) {
        // Définit la requête SQL pour supprimer les rôles d'un utilisateur.
    	String sql = "DELETE FROM Roles WHERE id_utilisateur = :id_utilisateur;";
    	
        // Prépare les paramètres pour la requête.
        MapSqlParameterSource source = new MapSqlParameterSource();
        
        // Ajoute l'ID de l'utilisateur.
        source.addValue("id_utilisateur", id_utilisateur); 
        
        // Exécute la mise à jour pour supprimer les rôles.
        namedParameterJdbcTemplate.update(sql, source);
    }
}
