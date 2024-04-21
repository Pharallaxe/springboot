package com.example.sorcier.bll.sqlserver;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.sorcier.bll.SorcierService;
import com.example.sorcier.bll.metier.SorcierMetier;
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.dal.sqlserver.MaisonRepositorySqlserver;
import com.example.sorcier.dal.sqlserver.SorcierRepositorySqlserver;

/**
 * Service pour la gestion des sorciers et des maisons sur SQL Server.
 */
@Service
@Profile("sqlserver")
public class SorcierServiceSqlserver implements SorcierService {
	
	private SorcierRepositorySqlserver sorcierRepositorySqlserver;
	private MaisonRepositorySqlserver maisonRepositorySqlserver;
	
	/**
	 * Constructeur pour initialiser les repositories pour sorciers et maisons.
	 * 
	 * @param sorcierRepositorySqlserver - Le pepository pour les sorciers.
	 * @param maisonRepositorySqlserver - Le repository pour les maisons.
	 */
	public SorcierServiceSqlserver(SorcierRepositorySqlserver sorcierRepositorySqlserver,
	                               MaisonRepositorySqlserver maisonRepositorySqlserver) {
		this.sorcierRepositorySqlserver = sorcierRepositorySqlserver;
		this.maisonRepositorySqlserver = maisonRepositorySqlserver;
	}
	
	/**
	 * Ajoute un sorcier à la base de données, s'il n'existe pas déjà.
	 * 
	 * @param sorcier - Le sorcier à ajouter.
	 */
	@Override
	public void ajouterSorcier(Sorcier sorcier) {
	    // Recherche si le sorcier existe déjà par son nom.
		Sorcier sorcierTrouve = sorcierRepositorySqlserver.findByName(sorcier);
		
		if (sorcierTrouve != null) {
		    // Affiche un message si le sorcier existe déjà et interrompt l'opération.
	    	System.out.println("SSS : Sorcier deja existant (ajouterSorcier).");
	    	return;
		}
		
		// Sauvegarde le sorcier dans la base de données.
		sorcierRepositorySqlserver.save(sorcier);
	}
	
	/**
	 * Ajoute un sorcier et sa maison associée à la base de données, s'ils n'existent pas déjà.
	 * 
	 * @param sorcier - Le sorcier à ajouter avec sa maison.
	 */
	@Override
	public void ajouterSorcierMaison(Sorcier sorcier){
	    // Recherche si le sorcier existe déjà par son nom.
		Sorcier sorcierTrouve = sorcierRepositorySqlserver.findByName(sorcier);
		
		if (sorcierTrouve != null) {
		    // Affiche un message si le sorcier existe déjà et interrompt l'opération.
	    	System.out.println("SSS : Sorcier deja existant (ajouterSorcierMaison).");
	    	return;
		}
		
	    // Récupère la maison associée au sorcier.
	    Maison maison = sorcier.getMaison();
	    
	    // Recherche si la maison existe déjà par son nom.
	    Maison maisonTrouvee = maisonRepositorySqlserver.findByName(maison);
	    
	    if (maisonTrouvee != null) {
	        // Affiche un message si la maison existe déjà et interrompt l'opération.
	    	System.out.println("SSS : Maison deja existante (ajouterSorcierMaison).");
	    	return;
	    }
	    
	    // Sauvegarde la maison dans la base de données.
        maisonRepositorySqlserver.save(maison);
        
        // Associe la maison sauvegardée au sorcier.
        sorcier.setMaison(maison);
        
        // Sauvegarde le sorcier dans la base de données.
        sorcierRepositorySqlserver.save(sorcier);
	}

	/**
	 * Modifie un sorcier existant dans la base de données.
	 * 
	 * @param sorcier - Le sorcier à modifier.
	 */
	@Override
	public void modifierSorcier(Sorcier sorcier) {
	    // Met à jour le sorcier dans la base de données.
		sorcierRepositorySqlserver.update(sorcier);
	}
	
	/**
	 * Récupère un sorcier par son identifiant.
	 * 
	 * @param id - L'id du sorcier à récupérer.
	 * @return Le sorcier récupéré.
	 */
	@Override
	public Sorcier recupererSorcier(int id) {
	    // Retourne le sorcier trouvé par son identifiant.
		return sorcierRepositorySqlserver.findById(id);
	}
	
	/**
	 * Récupère tous les sorciers de la base de données.
	 * 
	 * @return Liste de tous les sorciers.
	 */
	@Override
	public List<Sorcier> recupererSorciers() {
	    // Retourne tous les sorciers.
		return sorcierRepositorySqlserver.findAll();
	}
	
	/**
	 * Supprime un sorcier de la base de données par son identifiant.
	 * 
	 * @param id Identifiant du sorcier à supprimer.
	 */
	@Override
	public void supprimerSorcier(int id) {
	    // Supprime le sorcier par son identifiant.
		sorcierRepositorySqlserver.removeById(id);
	}
	
	/**
	 * Compte le nombre de sorciers et retourne une chaîne de caractères avec le total.
	 * 
	 * @return String représentant le nombre total de sorciers.
	 */
	@Override
	public String compterSorciers() {
	    // Compte et retourne le nombre total de sorciers sous forme de chaîne de caractères.
		return SorcierMetier.compterSorciers(recupererSorciers());
	}
	
	/**
	 * Recherche des sorciers contenant un mot-clé spécifique.
	 * 
	 * @param motARechercher - Le mot-clé utilisé pour la recherche.
	 * @return Liste des sorciers correspondants.
	 */
	@Override
	public List<Sorcier> rechercherSorciers(String motARechercher) {
	    // Recherche et retourne les sorciers dont le nom contient le mot-clé spécifié.
		return SorcierMetier.rechercherSorciers(motARechercher, recupererSorciers());
	}
}
