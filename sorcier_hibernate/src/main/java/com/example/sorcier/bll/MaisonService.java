package com.example.sorcier.bll;

import java.util.List;

import com.example.sorcier.bo.Maison;

/**
 * Interface pour les services de gestion des maisons.
 * Spécifie les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) nécessaires
 * pour manipuler les données des maisons dans l'application.
 */
public interface MaisonService {

    /**
     * Récupère toutes les maisons disponibles.
     * @return Une liste contenant toutes les maisons.
     */
    public List<Maison> getMaisons();
    
    /**
     * Ajoute une nouvelle maison.
     * @param maison La maison à ajouter.
     */
    public void ajouterMaison(Maison maison);
    
    /**
     * Récupère une maison par son identifiant.
     * @param id L'identifiant de la maison à récupérer.
     * @return La maison trouvée ou null si aucune maison n'a cet identifiant.
     */
    public Maison recupererMaison(int id);
    
    /**
     * Supprime une maison à partir de son identifiant.
     * @param id L'identifiant de la maison à supprimer.
     */
    public void supprimerMaison(int id);
    
    /**
     * Met à jour les informations d'une maison existante.
     * @param maison La maison avec les informations à mettre à jour.
     */
    public void modifierMaison(Maison maison);
    
    
	public List<Maison> rechercherMaisons(String motARechercher);
}
