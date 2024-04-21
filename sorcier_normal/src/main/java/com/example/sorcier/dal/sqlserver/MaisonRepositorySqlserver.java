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

import com.example.sorcier.bo.Maison;
import com.example.sorcier.dal.MaisonRepository;

/**
 * Implémentation de MaisonRepository pour SQL Server.
 */
@Repository
@Profile("sqlserver")
public class MaisonRepositorySqlserver implements MaisonRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Constructeur avec injection de dépendance via Spring.
	 *
	 * @param jdbcTemplate               - Template JDBC pour les opérations.
	 * @param namedParameterJdbcTemplate - Template JDBC pour les opérations avec
	 *                                   paramètres nommés.
	 */
	public MaisonRepositorySqlserver(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * Récupère toutes les maisons enregistrées dans la base de données.
	 *
	 * @return Liste de toutes les maisons disponibles.
	 */
	@Override
	public List<Maison> findAll() {
		// Définit la requête SQL pour récupérer toutes les maisons.
		String sql = "SELECT * FROM maison";
		
		// Utilise un BeanPropertyRowMapper pour mapper les résultats SQL sur les objets Maison.
		BeanPropertyRowMapper<Maison> mapper = new BeanPropertyRowMapper<>(Maison.class);
		
		// Exécute de la requête et retour des résultats.
		return jdbcTemplate.query(sql, mapper);
	}

	/**
	 * Trouve une maison par son identifiant.
	 *
	 * @param id - L'id de la maison à trouver.
	 * @return Maison trouvée ou null si aucune correspondance n'est trouvée.
	 */
	@Override
	public Maison findById(int id) {
		// Définit de la requête SQL avec paramètre nommé pour trouver une maison par son ID.
		String sql = "SELECT * FROM maison WHERE id = :id";
		
		// Prépare des paramètres de la requête.
		MapSqlParameterSource source = new MapSqlParameterSource().addValue("id", id);
		
		// Exécute de la requête et tentative de récupération de l'objet Maison.
		return namedParameterJdbcTemplate.queryForObject(sql, source, new BeanPropertyRowMapper<>(Maison.class));
	}

	/**
	 * Trouve une maison par son nom.
	 *
	 * @param maison - L'objet Maison contenant le nom à rechercher.
	 * @return Maison trouvée ou null si aucune correspondance n'est trouvée.
	 */
	@Override
	public Maison findByName(Maison maison) {
		// Définit la requête SQL avec paramètre nommé pour trouver une maison par son nom.
		String sql = "SELECT * FROM maison WHERE nom = :nom";
		
		// Prépare les paramètres de la requête.
		MapSqlParameterSource source = new MapSqlParameterSource().addValue("nom", maison.getNom());
		
		// Essaye de récupérer l'objet Maison avec gestion de l'exception si aucun résultat n'est trouvé.
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, source, new BeanPropertyRowMapper<>(Maison.class));
		} catch (EmptyResultDataAccessException e) {
			return null; // Retourne null si aucun résultat n'est trouvé.
		}
	}

	/**
	 * Supprime une maison par son identifiant.
	 *
	 * @param id - L'id de la maison à supprimer.
	 */
	@Override
	public void removeById(int id) {
		// Définit la requête SQL pour supprimer une maison par son ID.
		String sql = "DELETE FROM maison WHERE id = :id";
		
		// Prépare des paramètres de la requête.
		MapSqlParameterSource source = new MapSqlParameterSource().addValue("id", id);
		
		// Exécute la mise à jour pour supprimer la maison.
		namedParameterJdbcTemplate.update(sql, source);
	}

	/**
	 * Met à jour les informations d'une maison.
	 *
	 * @param maison - L'objet Maison contenant les nouvelles informations à mettre à jour.
	 */
	@Override
	public void update(Maison maison) {
		// Définit la requête SQL pour mettre à jour une maison.
		String sql = "UPDATE maison SET nom = :nom, bonusAttaque = :bonusAttaque, bonusSante = :bonusSante, modifiable = :modifiable WHERE id = :id";
		
		// Prépare des paramètres de la requête en utilisant BeanPropertySqlParameterSource pour lier les propriétés de l'objet Maison.
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(maison);
		
		// Exécute de la mise à jour.
		namedParameterJdbcTemplate.update(sql, source);
	}

	/**
	 * Enregistre une nouvelle maison dans la base de données.
	 *
	 * @param maison - L'objet Maison à enregistrer.
	 */
	@Override
	public void save(Maison maison) {
		// Prépare la clé générée pour insérer la maison.
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		// Définit la requête SQL pour insérer une nouvelle maison.
		String sql = "INSERT INTO maison (nom, bonusAttaque, bonusSante, modifiable) VALUES (:nom, :bonusAttaque, :bonusSante, :modifiable)";
		
		// Prépare les paramètres de la requête.
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(maison);
		
		// Exécute l'insertion et récupération de la clé générée.
		namedParameterJdbcTemplate.update(sql, source, keyHolder);
		
		// Attribut l'ID généré à l'objet Maison.
		if (keyHolder.getKey() != null) {
			maison.setId(keyHolder.getKey().intValue());
		}
	}
}
