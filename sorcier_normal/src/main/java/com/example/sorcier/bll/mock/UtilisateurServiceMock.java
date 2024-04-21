package com.example.sorcier.bll.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import com.example.sorcier.bll.UtilisateurService;
import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.dal.mock.UtilisateurRepositoryMock;
import com.example.sorcier.dal.mock.RoleRepositoryMock;


@Repository
@Profile("dev")
public class UtilisateurServiceMock implements UtilisateurService {
	
	private UtilisateurRepositoryMock utilisateurRepositoryMock;
	private RoleRepositoryMock roleRepositoryMock;

	@Autowired
	private UserDetailsManager userDetailsManager;

	// CONSTRUCTEUR

	/**
	 * Constructeur de UtilisateurServiceMock.
	 * 
	 * @param utilisateurRepositoryMock - Le mock du repository utilisateur.
	 * @param roleRepositoryMock        - Le mock du repository rôle.
	 */
	public UtilisateurServiceMock(UtilisateurRepositoryMock utilisateurRepositoryMock,
			RoleRepositoryMock roleRepositoryMock) {
		this.utilisateurRepositoryMock = utilisateurRepositoryMock;
		this.roleRepositoryMock = roleRepositoryMock;
	}

	// FONCTIONS DAL

	/**
	 * Ajoute un utilisateur avec un rôle spécifique.
	 * 
	 * @param utilisateur - L'utilisateur à ajouter.
	 * @param role -Le rôle de l'utilisateur.
	 */
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur, String role) {
		// Recherche l'utilisateur par nom dans le mock du repository utilisateur.
		Utilisateur utilisateurTrouve = utilisateurRepositoryMock.findByName(utilisateur.getNom());

		// Vérifie si l'utilisateur existe déjà.
		if (utilisateurTrouve != null) {
			System.out.println("USS : Utilisateur déjà existant (ajouterSorcier).");
			return;
		}

		// Met à jour le mot de passe de l'utilisateur.
		mettreAJourMdp(utilisateur);
		
		// Sauvegarde l'utilisateur dans le mock du repository utilisateur.
		utilisateurRepositoryMock.save(utilisateur);

		// Récupère l'identifiant de l'utilisateur nouvellement ajouté.
		int idCourant = utilisateurRepositoryMock.findByName(utilisateur.getNom()).getId();

		// Crée la liste des rôles de l'utilisateur.
		List<String> roles = new ArrayList<>();
		if (role.equals("ADMIN")) {
			roleRepositoryMock.save(idCourant, "ADMIN");
			roleRepositoryMock.save(idCourant, "INSCRIT");
			roles.add("ADMIN");
			roles.add("INSCRIT");
		}
		if (role.equals("INSCRIT")) {
			roleRepositoryMock.save(idCourant, "INSCRIT");
			roles.add("INSCRIT");
		}

		// Crée l'utilisateur Spring Security.
		User.UserBuilder userBuilder = User.withUsername(utilisateur.getNom()).password(utilisateur.getMdpHash())
				.roles(roles.toArray(new String[0]));
		userDetailsManager.createUser(userBuilder.build());
	}

	/**
	 * Modifie un utilisateur.
	 * 
	 * @param utilisateur - L'utilisateur à modifier.
	 */
	@Override
	public void modifierSorcier(Utilisateur utilisateur) {
		// Met à jour de l'utilisateur dans le mock du repository utilisateur.
		utilisateurRepositoryMock.update(utilisateur);
	}

	/**
	 * Modifie un utilisateur avec Met à jour le mot de passe.
	 * 
	 * @param utilisateur - L'utilisateur à modifier.
	 */
	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		// Met à jour le mot de passe de l'utilisateur.
		mettreAJourMdp(utilisateur);
		
		// Met à jour l'utilisateur dans le mock du repository utilisateur.
		utilisateurRepositoryMock.update(utilisateur);
	}

	/**
	 * Récupère un utilisateur par son identifiant.
	 * 
	 * @param id - L'id de l'utilisateur à récupérer
	 * @return L'utilisateur correspondant à l'identifiant spécifié.
	 */
	@Override
	public Utilisateur recupererUtilisateur(int id) {
		// Récupère l'utilisateur par son identifiant depuis le mock du repository utilisateur.
		return utilisateurRepositoryMock.findById(id);
	}

	/**
	 * Supprime un utilisateur par son identifiant.
	 * 
	 * @param id - L'id de l'utilisateur à supprimer.
	 */
	@Override
	public void supprimerUtilisateur(int id) {
		// Supprime le role de l'utilisateur.
		roleRepositoryMock.remove(id);
		
		// Supprime l'utilisateur dans le mock du repository utilisateur.
		utilisateurRepositoryMock.removeById(id);
	}

	/**
	 * Supprime un utilisateur par son nom.
	 * 
	 * @param nom Le nom de l'utilisateur à supprimer
	 */
	@Override
	public void supprimerUtilisateur(String nom) {
		// Récupère l'identifiant de l'utilisateur par son nom.
		int id = recupererUtilisateur(nom).getId();
		
		// Appelle la méthode de suppression par identifiant.
		supprimerUtilisateur(id);
	}

	/**
	 * Récupère un utilisateur par son nom.
	 * 
	 * @param nomUtilisateur - Le nom de l'utilisateur à récupérer.
	 * @return L'utilisateur correspondant au nom spécifié.
	 */
	@Override
	public Utilisateur recupererUtilisateur(String nomUtilisateur) {
		// Récupère l'utilisateur par son nom depuis le mock du repository d'utilisateur.
		return utilisateurRepositoryMock.findByName(nomUtilisateur);
	}

	// FONCTIONS METIER

	@Autowired
	private PasswordEncoder passwordEncoder; // Encodeur de mot de passe.

	/**
	 * Encode un mot de passe.
	 * 
	 * @param mdp - Le mot de passe à encoder.
	 * @return Le mot de passe encodé.
	 */
	public String encoderMDP(String mdp) {
		return passwordEncoder.encode(mdp);
	}

	/**
	 * Met à jour le mot de passe de l'utilisateur en le hashant.
	 * 
	 * @param utilisateur - L'utilisateur dont le mot de passe doit être mis à jour.
	 */
	public void mettreAJourMdp(Utilisateur utilisateur) {
		String motEncoder = encoderMDP(utilisateur.getMotDePasse().getMdp());
		utilisateur.setMdpHash(motEncoder);

		// EFFACER LE MDP EN MEMOIRE.
		utilisateur.getMotDePasse().setMdp(null);
		utilisateur.getMotDePasse().setConfirme(null);
	}
}
