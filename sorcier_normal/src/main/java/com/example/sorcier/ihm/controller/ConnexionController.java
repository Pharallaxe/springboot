package com.example.sorcier.ihm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Importe les annotations pour la gestion des contrôleurs Spring MVC.
import org.springframework.stereotype.Controller;
// Importe les annotations pour la gestion des requêtes GET et POST.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
// Importe les services de logique métier.
import com.example.sorcier.bll.SorcierService;
import com.example.sorcier.bll.UtilisateurService;
import com.example.sorcier.bll.metier.SorcierMetier;
// Importe les classes des objets de transfert de données.
import com.example.sorcier.bo.MotDePasse;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.bo.Utilisateur;

// Importe les annotations pour la validation des modèles.
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Contrôleur Spring MVC pour gérer les opérations liées à la connexion et au profil des utilisateurs.
 */
@Controller
public class ConnexionController {
	private SorcierService sorcierService;
	private UtilisateurService utilisateurService;

	/**
	 * Constructeur pour initialiser les services utilisés par ce contrôleur.
	 * 
	 * @param sorcierService - Le service de gestion des sorciers.
	 * @param utilisateurService - Le service de gestion des utilisateurs.
	 */
	public ConnexionController(SorcierService sorcierService, UtilisateurService utilisateurService) {
		this.sorcierService = sorcierService;
		this.utilisateurService = utilisateurService;
	}

	/**
	 * Méthode GET pour afficher la page de connexion.
	 * 
	 * @return Nom de la vue de connexion.
	 */
	@GetMapping("/connexion")
	public String afficherConnexion() {
		return "connexion";
	}

	/**
	 * Méthode GET pour afficher la page principale après déconnexion.
	 * 
	 * @return Redirection vers la page d'accueil.
	 */
	@GetMapping("/deconnexion")
	public String afficherDeconnexion() {
		return "index";
	}
	
	/**
	 * Méthode POST pour supprimer le compte utilisateur actuel.
	 * 
	 * @param principal Principal pour obtenir le nom de l'utilisateur courant.
	 * @return Redirection vers la page de déconnexion.
	 */
	@PostMapping("/supprimer")
    public String supprimerUtilisateur(Principal principal) {
		// Supprimer l'utilisateur actuel en utilisant son nom d'utilisateur.
		utilisateurService.supprimerUtilisateur(principal.getName());
        return "redirect:/deconnexion";
    }

	/**
	 * Méthode GET pour afficher le profil de l'utilisateur.
	 * 
	 * @param model Modèle pour passer des données à la vue.
	 * @param principal Principal pour obtenir le nom de l'utilisateur courant.
	 * @return Nom de la vue de profil.
	 */
	@GetMapping("/profil")
	public String afficherProfil(Model model, Principal principal) {
		// Récupérer la liste de tous les sorciers.
		List<Sorcier> sorciers = sorcierService.recupererSorciers();
		List<Map<String, Object>> sorciersALister = new ArrayList<>();
		for (Sorcier sorcier : sorciers) {
			Map<String, Object> sorcierMap = new HashMap<>();
			sorcierMap.put("id", sorcier.getId());
			sorcierMap.put("nom", SorcierMetier.afficherSorcier(sorcier));
			sorciersALister.add(sorcierMap);
		}

		// Récupérer le nom de l'utilisateur actuel.
		String nomUtilisateur = principal.getName();
		// Récupérer l'utilisateur actuel.
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(nomUtilisateur);

		// Ajouter des attributs au modèle pour la vue.
		model.addAttribute("utilisateurCourant", utilisateur);
		model.addAttribute("sorciersALister", sorciersALister);
		model.addAttribute("sorcierCourant", SorcierMetier.afficherSorcier(utilisateur));
		model.addAttribute("motDePasse", new MotDePasse());

		return "profil";
	}

	/**
	 * Méthode GET pour afficher la page d'inscription.
	 * 
	 * @param model Modèle pour passer des données à la vue.
	 * @return Le nom de la vue d'inscription.
	 */
	@GetMapping("/inscription")
	public String afficherInscription(Model model) {
		// Ajouter un nouvel utilisateur au modèle pour enregistrement.
		model.addAttribute("utilisateurACreer", new Utilisateur());

		return "inscription";
	}

	/**
	 * Méthode POST pour ajouter un nouvel utilisateur.
	 * 
	 * @param utilisateurACreer - L'utilisateur à créer, validé automatiquement par Spring MVC.
	 * @param role - Le rôle de l'utilisateur à ajouter.
	 * @param binding - Le résultat des contrôles de validation.
	 * @param model - Le modèle pour passer des données à la vue.
	 * @return La redirection vers la page de connexion ou de réinscription en cas d'erreur.
	 */
	@PostMapping("/inscription/lancer")
    public String ajouterUtilisateur(@Valid @ModelAttribute("utilisateurACreer") Utilisateur utilisateurACreer,
    								 @ModelAttribute("roleADonne") String role,
                                     BindingResult binding, Model model) {
		
        if (binding.hasErrors() || !utilisateurACreer.getMotDePasse().isIdentique()) {
            // Rediriger vers la page d'inscription en cas d'erreurs de validation.
            return "redirect:/inscription";
        }

        // Configurer le mot de passe hashé pour le nouvel utilisateur.
        utilisateurACreer.setMdpHash(utilisateurACreer.getMotDePasse().getMdp());
        
        // Ajouter l'utilisateur avec le rôle spécifié.
        utilisateurService.ajouterUtilisateur(utilisateurACreer, role);
        
        // Rediriger vers la page de connexion après l'inscription.
        return "redirect:/connexion";
    }
	
	/**
	 * Méthode POST pour changer le mot de passe de l'utilisateur.
	 * 
	 * @param mdp - Le nouveau mot de passe, validé automatiquement par Spring MVC.
	 * @param principal - Le Principal pour obtenir le nom de l'utilisateur courant.
	 * @param binding - Le résultat des contrôles de validation.
	 * @param model - Le modèle pour passer des données à la vue.
	 * @return La redirection vers la page de profil ou de réinitialisation en cas d'erreur.
	 */
	@PostMapping("/changerMDP")
	public String changerMDP(@Valid @ModelAttribute("nombre") MotDePasse mdp,
							 Principal principal,
							 BindingResult binding,
							 Model model) {
		
		if (binding.hasErrors() || !mdp.isIdentique()) {
			// Rediriger vers la page de profil en cas d'erreurs de validation.
			return "redirect:/profil";
		}
		
		// Récupérer le nom de l'utilisateur courant.
		String nomUtilisateur = principal.getName();
		
		// Récupérer l'utilisateur courant.
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(nomUtilisateur);
		
		// Mettre à jour le mot de passe de l'utilisateur.
		utilisateur.setMotDePasse(mdp);
		
		// Appliquer la modification de l'utilisateur.
		utilisateurService.modifierUtilisateur(utilisateur);
		
		// Rediriger vers la page de profil après la modification du mot de passe.
		return "redirect:/profil";
	}
	
	/**
	 * Méthode POST pour changer le sorcier associé à l'utilisateur.
	 * 
	 * @param id - L'id du sorcier à associer.
	 * @param principal - Le Principal pour obtenir le nom de l'utilisateur courant.
	 * @return La redirection vers la page de profil après modification.
	 */
	@PostMapping("/changerSorcier")
	public String changerSorcier(@RequestParam("idType") int id, Principal principal) {
		
		// Récupérer le nom de l'utilisateur courant.
		String nomUtilisateur = principal.getName();
		
		// Récupérer l'utilisateur courant.
		Utilisateur utilisateur = utilisateurService.recupererUtilisateur(nomUtilisateur);
		
		// Mettre à jour le sorcier associé à l'utilisateur.
		utilisateur.setSorcier(sorcierService.recupererSorcier(id));
		
		// Appliquer la modification de l'utilisateur.
		utilisateurService.modifierSorcier(utilisateur);
		
		// Rediriger vers la page de profil après modification du sorcier associé.
		return "redirect:/profil";
	}
}
