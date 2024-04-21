package com.example.sorcier.dal;

import com.example.sorcier.bo.Utilisateur;

public interface UtilisateurRepository {
    
    // FONCTIONS DAL
    
    /**
     * Récupère un utilisateur par son identifiant.
     * 
     * @param id - L'identifiant de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'identifiant spécifié.
     */
    public Utilisateur findById(int id);

    /**
     * Récupère un utilisateur par son nom.
     * 
     * @param utilisateur - L'utilisateur à rechercher.
     * @return L'utilisateur correspondant au nom spécifié.
     */
    public Utilisateur findByName(Utilisateur utilisateur);
    
    /**
     * Supprime un utilisateur par son identifiant.
     * 
     * @param id - L'identifiant de l'utilisateur à supprimer.
     */
    public void removeById(int id);

    /**
     * Sauvegarde un utilisateur.
     * 
     * @param utilisateur - L'utilisateur à sauvegarder.
     */
    public void save(Utilisateur utilisateur);
    
    /**
     * Met à jour un utilisateur.
     * 
     * @param utilisateur L'utilisateur à mettre à jour.
     */
    public void update(Utilisateur utilisateur);
    
    /**
     * Récupère un utilisateur par son nom.
     * 
     * @param nom - Le nom de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant au nom spécifié.
     */
    public Utilisateur findByName(String nom);
}
