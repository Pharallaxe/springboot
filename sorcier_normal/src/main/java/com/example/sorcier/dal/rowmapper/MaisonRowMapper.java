package com.example.sorcier.dal.rowmapper;

// Import pour utiliser le RowMapper de Spring.
import org.springframework.jdbc.core.RowMapper;

// Import de la classe Maison du package de l'objet de transfert de données.
import com.example.sorcier.bo.Maison;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper pour convertir les lignes de la base de données en objets Maison.
 * Utilisé avec JDBC pour mapper les lignes d'un ResultSet vers des objets
 * Maison.
 */
public class MaisonRowMapper implements RowMapper<Maison> {

	// Définition des constantes pour les noms des colonnes pour éviter les erreurs
	// de frappe.
	private String MAISON_ID = "maison_id";
	private String MAISON_NOM = "maison_nom";
	private String MAISON_BONUS_ATTAQUE = "maison_bonusAttaque";
	private String MAISON_BONUS_SANTE = "maison_bonusSante";
	private String MAISON_MODIFIABLE = "maison_modifiable";

	/**
	 * Mappe une ligne du ResultSet à un objet Maison.
	 * 
	 * @param rs- Le ResultSet à mapper.
	 * @param rowNum - Le numéro de la ligne actuelle.
	 * @return Un nouvel objet Maison initialisé avec les données de la ligne.
	 * @throws SQLException Si une erreur SQL survient.
	 */
	@Override
	public Maison mapRow(ResultSet rs, int rowNum) throws SQLException {
		// Crée une nouvelle instance de Maison en extrayant les données de chaque
		// colonne.
		return new Maison(
				// Récupère l'ID de la maison.
				rs.getInt(MAISON_ID),
				// Récupère le nom de la maison.
				rs.getString(MAISON_NOM),
				// Récupère le bonus d'attaque de la maison.
				rs.getInt(MAISON_BONUS_ATTAQUE),
				// Récupère le bonus de santé de la maison.
				rs.getInt(MAISON_BONUS_SANTE),
				// Récupère l'indicateur modifiable de la maison.
				rs.getBoolean(MAISON_MODIFIABLE));
	}
}
