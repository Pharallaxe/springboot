package com.example.sorcier.dal.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.bo.Sorcier;

/**
 * Mapper pour transformer les lignes d'une table SQL en instances de la classe Utilisateur.
 */
public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

    // Déclaration des constantes pour les noms des colonnes SQL.
    private String ID = "id";
    private String NOM = "nom";
    private String EMAIL = "email";
    private String MDP_HASH = "mdpHash";
    private String NIVEAU = "niveau";
    private String DATE_INSCRIPTION = "dateInscription";

    // Mapper pour les objets Sorcier associés à l'utilisateur.
    private SorcierRowMapper sorcierRowMapper = new SorcierRowMapper();

    /**
     * Mappe une ligne du ResultSet en une instance de Utilisateur.
     * 
     * @param rs - Le ResultSet à mapper.
     * @param rowNum - Le numéro de la ligne actuelle.
     * @return L'instance de Utilisateur correspondant à la ligne courante du ResultSet.
     * @throws SQLException - si une erreur SQL se produit.
     */
    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Récupère l'identifiant du Sorcier, si présent.
        Integer sorcierId = (Integer) rs.getObject("sorcier_id");
        
        // Initialisation de la référence à un sorcier.
        Sorcier sorcier = null;
        
        // Mappe le Sorcier si l'ID n'est pas null.
        if (sorcierId != null) {
            sorcier = sorcierRowMapper.mapRow(rs, rowNum);
        }

        // Crée une nouvelle instance d'Utilisateur avec les données extraites du ResultSet.
        return new Utilisateur(
            rs.getInt(ID),
            rs.getString(NOM),
            rs.getString(EMAIL),
            rs.getString(MDP_HASH),
            rs.getInt(NIVEAU),
            rs.getDate(DATE_INSCRIPTION),
            sorcier
        );
    }
}
