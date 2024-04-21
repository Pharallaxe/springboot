package com.example.sorcier.dal.sqlserver;

import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.dal.rowmapper.UtilisateurRowMapper;

//Annotations pour gérer les profils et le composant de repository.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//Gestion des exceptions pour les résultats de données vides.
import org.springframework.dao.EmptyResultDataAccessException;

//Template pour les opérations JDBC avec support des paramètres nommés.
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

//Support pour récupérer les clés générées.
import org.springframework.jdbc.support.GeneratedKeyHolder;

@Repository
@Profile("sqlserver")
public class UtilisateurRepositorySqlserver {
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    /**
     * Constructeur initialisant le template JDBC.
     *
     * @param namedParameterJdbcTemplate - Template JDBC pour les requêtes avec paramètres nommés.
     */
    public UtilisateurRepositorySqlserver(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    
    private final String sql_debut = "SELECT "
    		+ "u.id, u.nom, u.email, u.mdpHash, u.niveau, u.dateInscription, "
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
			+ "FROM utilisateur u "
			+ "LEFT JOIN sorcier s ON u.id_sorcier = s.id "
			+ "LEFT JOIN maison m ON s.id_maison = m.id ";
    
    /**
     * Retourne la requête SQL de base pour les opérations de recherche des utilisateurs.
     * 
     * @return String - La requête SQL de base pour obtenir les informations des utilisateurs.
     */
    public String getSql_debut() {
        // Retourne la chaîne SQL de base utilisée pour les requêtes.
        return sql_debut;
    }
    
    /**
     * Récupère un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à récupérer.
     * @return  L'utilisateur correspondant à l'identifiant spécifié.
     */
    public Utilisateur findById(int id) {
        // Concatène la condition WHERE à la requête SQL de base pour filtrer par ID.
        String sql = getSql_debut() + "WHERE u.id = :id";
        
        // Prépare les paramètres de la requête.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        
        // Exécute la requête pour trouver un utilisateur par son ID.
        return namedParameterJdbcTemplate.queryForObject(sql, source, new UtilisateurRowMapper());
    }
    
    /**
     * Supprime un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à supprimer.
     */
    public void removeById(int id) {
        // Construit la requête SQL pour supprimer un utilisateur par son ID.
        String sql = "DELETE FROM utilisateur WHERE id = :id;";
        
        // Prépare les paramètres de la requête.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        
        // Exécute la mise à jour pour supprimer l'utilisateur.
        namedParameterJdbcTemplate.update(sql, source);
    }
    
    /**
     * Sauvegarde un utilisateur dans la base de données.
     * 
     * @param utilisateur - L'utilisateur à sauvegarder.
     */
    public void save(Utilisateur utilisateur) {
        // Crée une clef pour l'insertion en base de données.
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        // Construit la requête SQL pour insérer un nouvel utilisateur.
        String sql = "INSERT INTO Utilisateur (nom, email, mdpHash, niveau, dateInscription, id_sorcier) VALUES " +
                     "(:nom, :email, :mdpHash, :niveau, :dateInscription, :idSorcier)";
        
        // Prépare les paramètres pour l'insertion.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("nom", utilisateur.getNom());
        source.addValue("email", utilisateur.getEmail());
        source.addValue("mdpHash", utilisateur.getMdpHash());
        source.addValue("niveau", utilisateur.getNiveau());
        source.addValue("dateInscription", utilisateur.getDateInscription());
        
        // Ajoute l'ID du sorcier associé si disponible, sinon null.
        source.addValue("idSorcier", utilisateur.getSorcier() != null ? utilisateur.getSorcier().getId() : null);
        
        // Exécute l'insertion et récupère la clé générée.
        namedParameterJdbcTemplate.update(sql, source, keyHolder);
        
        // Affecte l'ID récupéré à l'utilisateur si une clé est générée.
        if (keyHolder.getKey() != null) {
            utilisateur.setId(keyHolder.getKey().intValue());
        }
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     * 
     * @param utilisateur - L'utilisateur à mettre à jour.
     */
    public void update(Utilisateur utilisateur) {
        // Construit la requête SQL pour mettre à jour un utilisateur existant.
        String sql = "UPDATE Utilisateur SET nom = :nom, email = :email, mdpHash = :mdpHash, niveau = :niveau, "
    			+ "dateInscription = :dateInscription, id_sorcier = :id_sorcier WHERE id = :id";
        
        // Prépare les paramètres de la mise à jour.
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("nom", utilisateur.getNom());
        source.addValue("email", utilisateur.getEmail());
        source.addValue("mdpHash", utilisateur.getMdpHash());
        source.addValue("niveau", utilisateur.getNiveau());
        source.addValue("dateInscription", utilisateur.getDateInscription());
        
        // Ajoute l'ID du sorcier associé ou null si non disponible.
        source.addValue("id_sorcier", utilisateur.getSorcier() != null ? utilisateur.getSorcier().getId() : null);
        source.addValue("id", utilisateur.getId());
        
        // Exécute la mise à jour pour modifier les détails de l'utilisateur spécifié.
        namedParameterJdbcTemplate.update(sql, source);
    }
    
    /**
     * Récupère un utilisateur par son nom.
     * 
     * @param nomUtilisateur - Le nom de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant au nom spécifié, ou null si non trouvé.
     */
    public Utilisateur findByName(String nomUtilisateur) {
        // Construit la requête SQL pour trouver un utilisateur par son nom.
        String sql = getSql_debut() + "WHERE u.nom = :nom";
        
        // Prépare les paramètres de la requête avec le nom de l'utilisateur.
        MapSqlParameterSource source = new MapSqlParameterSource();
        
        source.addValue("nom", nomUtilisateur); // Ajoute le nom à la source de paramètres.
        try {
            // Tente de récupérer un utilisateur correspondant au nom spécifié.
            return namedParameterJdbcTemplate.queryForObject(sql, source, new UtilisateurRowMapper());
            
        } catch (EmptyResultDataAccessException e) {
            // Retourne null si aucun utilisateur correspondant n'est trouvé.
            return null;
        }
    }
}
