package com.example.sorcier.bll.sqlserver;

import java.util.List;

// Importe les annotations pour la configuration spécifique à un profil Spring.
import org.springframework.context.annotation.Profile;
// Importe les annotations pour déclarer la classe comme un service Spring.
import org.springframework.stereotype.Service;

// Importe les interfaces et classes de la couche de logique métier.
import com.example.sorcier.bll.MaisonService;
// Importe les objets de transfert de données.
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
// Importe les interfaces de la couche d'accès aux données.
import com.example.sorcier.dal.sqlserver.MaisonRepositorySqlserver;
import com.example.sorcier.dal.sqlserver.SorcierRepositorySqlserver;

/**
 * Implémentation SQL Server du service de gestion des maisons.
 */
@Service
@Profile("sqlserver")
public class MaisonServiceSqlserver implements MaisonService {

	private MaisonRepositorySqlserver maisonRepositorySqlserver;
	private SorcierRepositorySqlserver sorcierRepositorySqlserver;

	/**
	 * Constructeur pour initialiser les référentiels SQL Server.
	 * 
	 * @param maisonRepositorySqlserver - L'ensemble des opérations CRUD sur les maisons.
	 * @param sorcierRepositorySqlserver - L'ensemble des opérations CRUD sur les sorciers.
	 */
	public MaisonServiceSqlserver(MaisonRepositorySqlserver maisonRepositorySqlserver,
			SorcierRepositorySqlserver sorcierRepositorySqlserver) {
		this.maisonRepositorySqlserver = maisonRepositorySqlserver;
		this.sorcierRepositorySqlserver = sorcierRepositorySqlserver;
	}
	
	/**
	 * Ajoute une nouvelle maison dans la base de données.
	 * 
	 * @param maison Maison à ajouter.
	 */
	@Override
	public void ajouterMaison(Maison maison) {
		// Recherche une maison existante avec le même nom.
		Maison maisonTrouvee = maisonRepositorySqlserver.findByName(maison);
		
		if (maisonTrouvee != null) {
	    	// Afficher un message si la maison existe déjà.
	    	System.out.println("MSS : Maison deja existante (ajouterMaison).");
	    	return;
		}
		// Sauvegarder la nouvelle maison dans la base de données.
		maisonRepositorySqlserver.save(maison);
	}
	
	/**
	 * Modifie une maison existante dans la base de données.
	 * 
	 * @param maison - La maison avec les nouvelles informations à mettre à jour.
	 */
	@Override
	public void modifierMaison(Maison maison) {
		// Mettre à jour la maison dans la base de données.
		maisonRepositorySqlserver.update(maison);
	}
	
	/**
	 * Récupère une maison par son identifiant.
	 * 
	 * @param id - L'id de la maison.
	 * @return Maison récupérée.
	 */
	@Override
	public Maison recupererMaison(int id) {
		// Rechercher et retourner la maison par ID.
		return maisonRepositorySqlserver.findById(id);
	}
	
	/**
	 * Récupère toutes les maisons disponibles.
	 * 
	 * @return Liste de toutes les maisons.
	 */
	@Override
	public List<Maison> recupererMaisons() {
		// Rechercher et retourner toutes les maisons.
		return maisonRepositorySqlserver.findAll();
	}

	/**
	 * Supprime une maison de la base de données.
	 * 
	 * @param id_maison - L'id de la maison à supprimer.
	 */
	@Override
	public void supprimerMaison(int id_maison) {
		// Vérifie si des sorciers sont encore associés à cette maison.
		List<Sorcier> sorciersTrouve = sorcierRepositorySqlserver.findByMaison(id_maison);
		
		if (!sorciersTrouve.isEmpty()) {
	    	// Afficher un message si la maison est encore utilisée.
	    	System.out.println("MSS : Maison utilisee (supprimerMaison).");
	    	return;
		}
		// Supprimer la maison de la base de données si aucune dépendance n'est trouvée.
		maisonRepositorySqlserver.removeById(id_maison);
	}

	/**
	 * Recherche des maisons contenant un mot spécifique dans leur nom.
	 * 
	 * @param motARechercher - Le mot-clé pour la recherche.
	 * @return Liste des maisons correspondant au critère de recherche.
	 */
	@Override
	public List<Maison> rechercherMaisons(String motARechercher) {
		if (motARechercher.isEmpty()) {
			// Retourner toutes les maisons si aucun mot-clé n'est fourni.
			return recupererMaisons();
			
		} else {
			// Filtrer les maisons dont le nom contient le mot-clé, insensible à la casse.
			return recupererMaisons().stream()
					.filter(m -> m.getNom().toLowerCase().contains(motARechercher.toLowerCase()))
					.toList();
		}
	}
}
