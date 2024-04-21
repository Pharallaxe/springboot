package com.example.sorcier.dal;

import java.util.List;

import com.example.sorcier.bo.Sorcier;

public interface SorcierRepository {

    // FONCTIONS DAL
    
    /**
     * Récupère tous les sorciers.
     * 
     * @return La liste de tous les sorciers.
     */
    public List<Sorcier> findAll();
    
    /**
     * Récupère un sorcier par son identifiant.
     * 
     * @param id - L'id du sorcier à récupérer.
     * @return Le sorcier correspondant à l'identifiant spécifié.
     */
    public Sorcier findById(int id);
    
    /**
     * Récupère tous les sorciers appartenant à une maison spécifique.
     * 
     * @param id_maison - L'id de la maison.
     * @return La liste des sorciers appartenant à la maison spécifiée.
     */
	List<Sorcier> findByMaison(int id_maison);

    /**
     * Récupère un sorcier par son nom.
     * 
     * @param sorcier - Le sorcier à rechercher.
     * @return Le sorcier correspondant au nom spécifié.
     */
	Sorcier findByName(Sorcier sorcier);
    
    /**
     * Supprime un sorcier par son identifiant.
     * 
     * @param id - L'id du sorcier à supprimer.
     */
    public void removeById(int id);

    /**
     * Sauvegarde un sorcier.
     * 
     * @param sorcier - Le sorcier à sauvegarder
     */
    public void save(Sorcier sorcier);
    
    /**
     * Met à jour un sorcier.
     * 
     * @param sorcier - Le sorcier à mettre à jour.
     */
    public void update(Sorcier sorcier);
}
