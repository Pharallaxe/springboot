package com.example.sorcier.bll;

import java.util.List;

import com.example.sorcier.bo.Sorcier;

/**
 * Interface définissant les services pour la gestion des sorciers.
 * Spécifie les opérations CRUD nécessaires pour manipuler les données des sorciers,
 * ainsi qu'une opération supplémentaire pour compter le nombre de sorciers.
 */
public interface SorcierService {
	
    /**
     * Récupère tous les sorciers disponibles.
     * @return Une liste de tous les sorciers.
     */
	public List<Sorcier> getSorciers();
	
	/**
     * Ajoute un nouveau sorcier.
     * @param sorcier Le sorcier à ajouter.
     */
	public void ajouterSorcier(Sorcier sorcier);
	
	/**
     * Récupère un sorcier par son identifiant.
     * @param id L'identifiant du sorcier.
     * @return Le sorcier trouvé ou null si aucun sorcier n'a cet identifiant.
     */
	public Sorcier recupererSorcier(int id);
	
	/**
     * Supprime un sorcier à partir de son identifiant.
     * @param id L'identifiant du sorcier à supprimer.
     */
	public void supprimerSorcier(int id);
	
	/**
     * Met à jour les informations d'un sorcier existant.
     * @param sorcier Le sorcier avec les informations mises à jour.
     */
	public void modifierSorcier(Sorcier sorcier);
	
	/**
     * Compte le nombre total de sorciers.
     * @return Une chaîne de caractères indiquant le nombre de sorciers.
     */
	public String compterSorciers();

	public List<Sorcier> rechercherSorciers(String motARechercher);
}
