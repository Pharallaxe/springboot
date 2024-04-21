package com.example.sorcier.bll;

import com.example.sorcier.bo.Utilisateur;

public interface UtilisateurService {
	
	// FONCTION DAL
	
	/**
     * Ajoute un utilisateur avec un rôle spécifique.
     * 
     * @param utilisateur - L'utilisateur à ajouter
     * @param role - Le rôle de l'utilisateur
     */
	public void ajouterUtilisateur(Utilisateur utilisateur, String role);
		
	/**
     * Modifie un utilisateur.
     * 
     * @param utilisateur - L'utilisateur à modifier
     */
	public void modifierSorcier(Utilisateur utilisateur);

	/**
     * Modifie un utilisateur.
     * 
     * @param utilisateur - L'utilisateur à modifier
     */
	public void modifierUtilisateur(Utilisateur utilisateur);
	
	/**
     * Récupère un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à récupérer
     * @return L'utilisateur correspondant à l'identifiant spécifié
     */
	public Utilisateur recupererUtilisateur(int id);
		
	/**
     * Supprime un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à supprimer
     */
	public void supprimerUtilisateur(int id);

	/**
     * Récupère un utilisateur par son nom.
     * 
     * @param nomUtilisateur - Le nom de l'utilisateur à récupérer
     * @return L'utilisateur correspondant au nom spécifié
     */
	public Utilisateur recupererUtilisateur(String nomUtilisateur);

	/**
     * Supprime un utilisateur par son nom.
     * 
     * @param nom - Le nom de l'utilisateur à supprimer
     */
	void supprimerUtilisateur(String nom);
}
