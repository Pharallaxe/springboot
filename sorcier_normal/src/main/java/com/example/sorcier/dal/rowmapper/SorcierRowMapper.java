package com.example.sorcier.dal.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper pour convertir les lignes de la base de données en objets Sorcier.
 * Utilisé avec JDBC pour mapper les lignes d'un ResultSet vers des objets
 * Sorcier, incluant les données associées de Maison.
 */
public class SorcierRowMapper implements RowMapper<Sorcier> {

	// Définit les constantes pour les noms des colonnes pour éviter les erreurs de
	// frappe et augmenter la maintenabilité.
	private String SORCIER_ID = "sorcier_id";
	private String SORCIER_NOM = "sorcier_nom";
	private String SORCIER_PRENOM = "sorcier_prenom";
	private String SORCIER_ATTAQUE = "sorcier_attaque";
	private String SORCIER_SANTE = "sorcier_sante";
	private String SORCIER_MODIFIABLE = "sorcier_modifiable";

	// Utilise un MaisonRowMapper pour mapper les informations de Maison associées à
	// un Sorcier.
	private final MaisonRowMapper maisonRowMapper = new MaisonRowMapper();

	/**
	 * Mappe une ligne du ResultSet à un objet Sorcier.
	 * 
	 * @param rs     Le ResultSet à mapper.
	 * @param rowNum Le numéro de la ligne actuelle.
	 * @return Un nouvel objet Sorcier initialisé avec les données de la ligne.
	 * @throws SQLException Si une erreur SQL survient.
	 */
	@Override
	public Sorcier mapRow(ResultSet rs, int rowNum) throws SQLException {
		// Utilise MaisonRowMapper pour obtenir une instance de Maison à partir de la
		// ligne courante du ResultSet.
		Maison maison = maisonRowMapper.mapRow(rs, rowNum);

		// Crée une nouvelle instance de Sorcier en extrayant les données de chaque
		// colonne spécifiée.
		return new Sorcier(
				// Récupère l'ID du sorcier.
				rs.getInt(SORCIER_ID),
				// Récupère le nom du sorcier.
				rs.getString(SORCIER_NOM),
				// Récupère le prénom du sorcier.
				rs.getString(SORCIER_PRENOM),
				// Associe l'instance de Maison récupérée.
				maison,
				// Récupère le niveau d'attaque du sorcier.
				rs.getInt(SORCIER_ATTAQUE),
				// Récupère le niveau de santé du sorcier.
				rs.getInt(SORCIER_SANTE),
				// Récupère l'indicateur si le sorcier est modifiable.
				rs.getBoolean(SORCIER_MODIFIABLE));
	}
}
