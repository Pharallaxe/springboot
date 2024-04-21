package com.example.sorcier.dal.sqlserver;

import java.util.List;

//Annotations pour gérer les profils et le composant de repository.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//Gestion des exceptions pour les résultats de données vides.
import org.springframework.dao.EmptyResultDataAccessException;

//Mapper pour lier les propriétés des beans Java avec les colonnes de la base de données.
import org.springframework.jdbc.core.BeanPropertyRowMapper;

//Template pour les opérations JDBC.
import org.springframework.jdbc.core.JdbcTemplate;

//Template pour les opérations JDBC avec support des paramètres nommés.
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//Support pour récupérer les clés générées.
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.dal.SorcierRepository;
import com.example.sorcier.dal.rowmapper.SorcierRowMapper;

@Repository
@Profile("sqlserver")
public class SorcierRepositorySqlserver implements SorcierRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String sql_debut = "SELECT "
    		+ "s.id AS sorcier_id, "
    		+ "s.nom AS sorcier_nom, "
    		+ "s.prenom AS sorcier_prenom, "
    		+ "s.attaque AS sorcier_attaque, "
    		+ "s.sante AS sorcier_sante, "
    		+ "s.modifiable AS sorcier_modifiable, "
    		+ "m.id AS maison_id, "
    		+ "m.nom AS maison_nom, "
    		+ "m.bonusAttaque AS maison_bonusAttaque, "
    		+ "m.bonusSante AS maison_bonusSante, "
    		+ "m.modifiable AS maison_modifiable "
    		+ "FROM sorcier s "
    		+ "INNER JOIN maison m ON s.id_maison = m.id ";

    /**
     * Retourne la requête SQL de base pour les opérations de recherche.
     * 
     * @return La requête SQL de base pour obtenir les informations des sorciers.
     */
    public String getSql_debut() {
        // Retourne la chaîne SQL de base utilisée pour les requêtes.
        return sql_debut;
    }

    /**
     * Constructeur initialisant les templates JDBC.
     *
     * @param jdbcTemplate - Template JDBC standard pour les opérations SQL.
     * @param namedParameterJdbcTemplate - Template JDBC pour les requêtes avec paramètres nommés.
     */
    public SorcierRepositorySqlserver(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        // Initialise le JdbcTemplate avec l'instance fournie.
        this.jdbcTemplate = jdbcTemplate;
        
        // Initialise le NamedParameterJdbcTemplate avec l'instance fournie.
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Récupère tous les sorciers.
     * 
     * @return La liste de tous les sorciers
     */
    @Override
    public List<Sorcier> findAll() {
        // Construit la requête SQL complète en ajoutant un point-virgule.
        String sql = getSql_debut() + ";";
        
        // Exécute la requête pour récupérer tous les sorciers.
        return jdbcTemplate.query(sql, new SorcierRowMapper());
    }

    /**
     * Récupère un sorcier par son identifiant.
     * 
     * @param id - L'id du sorcier à récupérer.
     * @return Le sorcier correspondant à l'identifiant spécifié.
     */
    @Override
    public Sorcier findById(int id) {
        // Ajoute la condition WHERE à la requête SQL de base pour filtrer par ID.
        String sql = getSql_debut() + "WHERE s.id = :id;";
        
        // Prépare les paramètres de la requête.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        
        // Exécute la requête pour trouver un sorcier par son ID.
        return namedParameterJdbcTemplate.queryForObject(sql, source, new SorcierRowMapper());
    }
    
    /**
     * Récupère tous les sorciers appartenant à une maison spécifique.
     * 
     * @param id_maison - L'id de la maison.
     * @return La liste des sorciers appartenant à la maison spécifiée.
     */    
    @Override
    public List<Sorcier> findByMaison(int id_maison) {
        // Construit la requête SQL pour récupérer tous les sorciers d'une maison spécifique.
        String sql = "SELECT * FROM sorcier WHERE id_maison = :id_maison;";
        
        // Prépare les paramètres de la requête avec l'ID de la maison.
        MapSqlParameterSource source = new MapSqlParameterSource().addValue("id_maison", id_maison);
        
        // Exécute la requête pour trouver les sorciers par maison.
        return namedParameterJdbcTemplate.query(sql, source, new BeanPropertyRowMapper<>(Sorcier.class));
    }
    
    /**
     * Récupère un sorcier par son nom.
     * 
     * @param sorcier - Le sorcier à rechercher.
     * @return Le sorcier correspondant au nom spécifié.
     */
    @Override
    public Sorcier findByName(Sorcier sorcier) {
        // Construit la requête SQL pour trouver un sorcier par nom et prénom.
        String sql = "SELECT * FROM sorcier WHERE nom = :nom AND prenom = :prenom;";
        
        // Prépare les paramètres de la requête avec le nom et prénom du sorcier.
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("nom", sorcier.getNom())
                .addValue("prenom", sorcier.getPrenom());
        
        try {
            // Tente de récupérer un sorcier correspondant aux critères de nom et prénom.
            return namedParameterJdbcTemplate.queryForObject(sql, source, new BeanPropertyRowMapper<>(Sorcier.class));
            
        } catch (EmptyResultDataAccessException e) {
            // Retourne null si aucun sorcier correspondant n'est trouvé.
            return null;
        }
    }

    
    /**
     * Supprime un sorcier par son identifiant.
     * 
     * @param id - L'id du sorcier à supprimer.
     */
    @Override
    public void removeById(int id) {
        // Construit la requête SQL pour supprimer un sorcier par son ID.
        String sql = "DELETE FROM sorcier WHERE id = :id;";
        
        // Prépare les paramètres de la requête avec l'ID du sorcier.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        
        // Exécute la mise à jour pour supprimer le sorcier spécifié.
        namedParameterJdbcTemplate.update(sql, source);
    }

    
    /**
     * Sauvegarde un sorcier.
     * 
     * @param sorcier - Le sorcier à sauvegarder
     */
    @Override
    public void save(Sorcier sorcier) {
        // Construit la requête SQL pour insérer un nouveau sorcier dans la base de données.
        String sql = "INSERT INTO sorcier (nom, prenom, id_maison, attaque, sante, modifiable) VALUES " +
                "(:nom, :prenom, :maison.id, :attaque, :sante, :modifiable);";
        
        // Prépare les paramètres de l'insertion à partir des propriétés de l'objet Sorcier.
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(sorcier);
        
        // Prépare la clé générée pour l'insertion.
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        // Exécute l'insertion et récupère la clé primaire générée.
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        
        // Affecte l'ID récupéré au sorcier si une clé est générée.
        if (keyHolder.getKey() != null) {
            sorcier.setId(keyHolder.getKey().intValue());
        }
    }

    /**
     * Met à jour un sorcier.
     * 
     * @param sorcier - Le sorcier à mettre à jour
     */
    @Override
    public void update(Sorcier sorcier) {
        // Construit la requête SQL pour mettre à jour un sorcier existant.
        String sql = "UPDATE sorcier SET nom = :nom, prenom = :prenom, id_maison = :maison.id, " +
                "attaque = :attaque, sante = :sante, modifiable = :modifiable WHERE id = :id";
        
        // Prépare les paramètres de la mise à jour à partir des propriétés du sorcier.
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(sorcier);
        
        // Exécute la mise à jour pour modifier les détails du sorcier spécifié.
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
