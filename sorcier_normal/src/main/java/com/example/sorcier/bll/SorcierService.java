package com.example.sorcier.bll;

import java.util.List;

import com.example.sorcier.bo.Sorcier;

public interface SorcierService {
	
	// FONCTIONS DAL
	
	/**
     * Ajoute un sorcier.
     * 
     * @param sorcier Le sorcier à ajouter
     */
	public void ajouterSorcier(Sorcier sorcier);
	
	/**
     * Ajoute un sorcier à une maison.
     * 
     * @param sorcier Le sorcier à ajouter
     */
	void ajouterSorcierMaison(Sorcier sorcier);
	
	/**
     * Modifie un sorcier.
     * 
     * @param sorcier Le sorcier à modifier
     */
	public void modifierSorcier(Sorcier sorcier);
	
	/**
     * Récupère un sorcier par son identifiant.
     * 
     * @param id L'identifiant du sorcier à récupérer
     * @return Le sorcier correspondant à l'identifiant spécifié
     */
	public Sorcier recupererSorcier(int id);
	
	/**
     * Récupère tous les sorciers.
     * 
     * @return La liste de tous les sorciers
     */
	public List<Sorcier> recupererSorciers();
	
	/**
     * Supprime un sorcier par son identifiant.
     * 
     * @param id L'identifiant du sorcier à supprimer
     */
	public void supprimerSorcier(int id);
	
	// FONCTIONS METIER
	
	/**
     * Compte le nombre de sorciers.
     * 
     * @return Le nombre de sorciers
     */
	public String compterSorciers();

	/**
     * Recherche les sorciers par un mot spécifique.
     * 
     * @param motARechercher Le mot à rechercher dans les noms des sorciers
     * @return La liste des sorciers correspondant au critère de recherche
     */
	public List<Sorcier> rechercherSorciers(String motARechercher);
}
