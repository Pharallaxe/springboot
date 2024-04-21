package com.example.sorcier.bll;

import java.util.List;

import com.example.sorcier.bo.Maison;

public interface MaisonService {

	// FONCTIONS DAL
	
    /**
     * Ajoute une maison.
     * 
     * @param maison La maison à ajouter
     */
    public void ajouterMaison(Maison maison);
        
    /**
     * Modifie une maison.
     * 
     * @param maison La maison à modifier
     */
    public void modifierMaison(Maison maison);
    
    /**
     * Récupère une maison par son identifiant.
     * 
     * @param id L'identifiant de la maison à récupérer
     * @return La maison correspondant à l'identifiant spécifié
     */
    public Maison recupererMaison(int id);
    
    /**
     * Récupère toutes les maisons.
     * 
     * @return La liste de toutes les maisons
     */
    public List<Maison> recupererMaisons();
    
    /**
     * Supprime une maison par son identifiant.
     * 
     * @param id L'identifiant de la maison à supprimer
     */
    public void supprimerMaison(int id);
    
    // FONCTIONS METIER
    
    /**
     * Recherche les maisons par un mot spécifique.
     * 
     * @param motARechercher Le mot à rechercher dans les noms des maisons
     * @return La liste des maisons correspondant au critère de recherche
     */
	public List<Maison> rechercherMaisons(String motARechercher);

}
