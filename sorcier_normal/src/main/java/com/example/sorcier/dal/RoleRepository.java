package com.example.sorcier.dal;

public interface RoleRepository {

    // FONCTIONS DAL
    
    /**
     * Sauvegarde un rôle pour un utilisateur spécifique.
     * 
     * @param id_utilisateur - L'id de l'utilisateur.
     * @param role - Le rôle à sauvegarder.
     */
    public void save(int id_utilisateur, String role);

    /**
     * Supprime tous les rôles associés à un utilisateur.
     * 
     * @param id_utilisateur - L'id de l'utilisateur.
     */
    public void remove(int id_utilisateur);

}
