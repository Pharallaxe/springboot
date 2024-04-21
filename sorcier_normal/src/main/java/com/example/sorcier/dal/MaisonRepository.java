package com.example.sorcier.dal;

import java.util.List;

import com.example.sorcier.bo.Maison;

public interface MaisonRepository {
    
    // FONCTIONS DAL
    
    /**
     * Récupère toutes les maisons.
     * 
     * @return La liste de toutes les maisons
     */
    public List<Maison> findAll();
    
    /**
     * Récupère une maison par son identifiant.
     * 
     * @param id L'identifiant de la maison à récupérer
     * @return La maison correspondant à l'identifiant spécifié
     */
    public Maison findById(int id);

    /**
     * Récupère une maison par son nom.
     * 
     * @param maison La maison à rechercher
     * @return La maison correspondant au nom spécifié
     */
    public Maison findByName(Maison maison);
    
    /**
     * Supprime une maison par son identifiant.
     * 
     * @param id L'identifiant de la maison à supprimer
     */
    public void removeById(int id);

    /**
     * Sauvegarde une maison.
     * 
     * @param maison La maison à sauvegarder
     */
    public void save(Maison maison);
    
    /**
     * Met à jour une maison.
     * 
     * @param maison La maison à mettre à jour
     */
    public void update(Maison maison);
}
