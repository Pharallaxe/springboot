package com.example.sorcier.bll.sqlserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.sorcier.bll.UtilisateurService;
import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.dal.sqlserver.RoleRepositorySqlserver;
import com.example.sorcier.dal.sqlserver.UtilisateurRepositorySqlserver;


@Repository
@Profile("sqlserver")
public class UtilisateurServiceSqlserver implements UtilisateurService{
	private UtilisateurRepositorySqlserver utilisateurRepositorySqlserver;
	private RoleRepositorySqlserver roleRepositorySqlserver;

	/**
	 * Constructeur pour initialiser les repositories nécessaires.
	 * 
	 * @param utilisateurRepositorySqlserver - Le repository pour les utilisateurs.
	 * @param roleRepositorySqlserver - Le repository pour les rôles.
	 */
	public UtilisateurServiceSqlserver(UtilisateurRepositorySqlserver utilisateurRepositorySqlserver,
			RoleRepositorySqlserver roleRepositorySqlserver) {
		this.utilisateurRepositorySqlserver = utilisateurRepositorySqlserver; // Initialise le repository des utilisateurs.
		this.roleRepositorySqlserver = roleRepositorySqlserver; // Initialise le repository des rôles.
	}
	
	// Fonctions DAL
	
	
	/**
     * Ajoute un utilisateur avec un rôle spécifique.
     * 
     * @param utilisateur - L'utilisateur à ajouter
     * @param role - Le rôle de l'utilisateur
     */
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur, String role) {
		// Recherche un utilisateur par nom pour vérifier son existence.
		Utilisateur utilisateurTrouve = utilisateurRepositorySqlserver.findByName(utilisateur.getNom());	
		
		// Vérifie si l'utilisateur existe déjà pour éviter les doublons.
		if (utilisateurTrouve != null) {
	    	System.out.println("USS : Utilisateur deja existant (ajouterSorcier).");
	    	return;
		}
		
		// Met à jour le mot de passe de l'utilisateur.
		mettreAJourMdp(utilisateur);
		
		// Sauvegarde le nouvel utilisateur dans la base de données.
		utilisateurRepositorySqlserver.save(utilisateur);
		
		// Récupère l'identifiant de l'utilisateur nouvellement ajouté.
		int idCourant = utilisateurRepositorySqlserver.findByName(utilisateur.getNom()).getId();
	
		
		// Attribue les rôles à l'utilisateur selon le rôle spécifié.
		if (role.equals("ADMIN")) {
			roleRepositorySqlserver.save(idCourant, "ROLE_ADMIN");
			roleRepositorySqlserver.save(idCourant, "ROLE_INSCRIT");
		}
		if (role.equals("INSCRIT")) {
			roleRepositorySqlserver.save(idCourant, "ROLE_INSCRIT");
		}
	}

	/**
     * Modifie un utilisateur.
     * 
     * @param utilisateur - L'utilisateur à modifier
     */
	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		// Met à jour le mot de passe de l'utilisateur.
		mettreAJourMdp(utilisateur);
		// Met à jour l'utilisateur dans la base de données.
		utilisateurRepositorySqlserver.update(utilisateur);
	}
	
	/**
     * Modifie un utilisateur.
     * 
     * @param utilisateur - L'utilisateur à modifier
     */
	@Override
	public void modifierSorcier(Utilisateur utilisateur) {
		utilisateurRepositorySqlserver.update(utilisateur);
	}
	
	/**
     * Récupère un utilisateur par son nom.
     * 
     * @param nomUtilisateur - Le nom de l'utilisateur à récupérer
     * @return L'utilisateur correspondant au nom spécifié
     */
	@Override
	public Utilisateur recupererUtilisateur(String nomUtilisateur) {
		// Récupère un utilisateur par son nom.
		return utilisateurRepositorySqlserver.findByName(nomUtilisateur);
	}

	/**
     * Récupère un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à récupérer
     * @return L'utilisateur correspondant à l'identifiant spécifié
     */
	@Override
	public Utilisateur recupererUtilisateur(int id) {
		// Récupère un utilisateur par son identifiant.
		return utilisateurRepositorySqlserver.findById(id);
	}
	
	/**
     * Supprime un utilisateur par son identifiant.
     * 
     * @param id - L'id de l'utilisateur à supprimer
     */
	@Override
	public void supprimerUtilisateur(int id) {
		// Supprime les rôles associés à l'utilisateur.
		roleRepositorySqlserver.remove(id);
		// Supprime l'utilisateur de la base de données.
		utilisateurRepositorySqlserver.removeById(id);
	}
	
	/**
     * Supprime un utilisateur par son nom.
     * 
     * @param nom - Le nom de l'utilisateur à supprimer
     */
	@Override
	public void supprimerUtilisateur(String nom) {
		// Récupère un utilisateur par son nom pour obtenir son identifiant.
		int id = recupererUtilisateur(nom).getId();
		// Supprime l'utilisateur par son identifiant.
		supprimerUtilisateur(id);
	}

	// Fonctions Métiers
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/**
	 * Encode un mot de passe en utilisant un algorithme de hashage.
	 * 
	 * @param mdp - Le mot de passe à encoder.
	 * @return String - Le mot de passe encodé.
	 */
	public String encoderMDP(String mdp) {
		// Encode le mot de passe.
		String motDePasseEncode = passwordEncoder.encode(mdp);
        return motDePasseEncode;
	
	}
	
	/**
	 * Met à jour le hash du mot de passe de l'utilisateur.
	 * 
	 * @param utilisateur - L'utilisateur dont le mot de passe doit être mis à jour.
	 */
	public void mettreAJourMdp(Utilisateur utilisateur) {
		// Encode le mot de passe et le définit comme nouveau hash.
		String motEncoder = encoderMDP(utilisateur.getMotDePasse().getMdp());
		utilisateur.setMdpHash(motEncoder);
		
		// Efface le mot de passe en mémoire pour des raisons de sécurité.
		utilisateur.getMotDePasse().setMdp(null);
		utilisateur.getMotDePasse().setConfirme(null);
	}
}
