package com.example.sorcier.ihm.controller;

import java.util.List;

// Importations nécessaires pour les fonctionnalités du contrôleur
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Importations des services et objets métiers (business objects)
import com.example.sorcier.bll.MaisonService;
import com.example.sorcier.bll.SorcierService;
import com.example.sorcier.bo.Sorcier;

import jakarta.validation.Valid;

/**
 * Contrôleur pour la gestion des sorciers dans l'application. Fournit des
 * méthodes pour lister, ajouter, détailler, supprimer et modifier des sorciers.
 */
@Controller
public class SorcierController {
    private SorcierService sorcierService;
    private MaisonService maisonService;
    
    /**
     * Constructeur pour l'injection de dépendances des services utilisés.
     * @param sorcierService Le service pour la gestion des sorciers.
     * @param maisonService Le service pour la gestion des maisons.
     */
    public SorcierController(SorcierService sorcierService, MaisonService maisonService) {
        this.sorcierService = sorcierService;
        this.maisonService = maisonService;
    }

	/**
	 * Méthode pour lister tous les sorciers et les maisons disponibles.
	 * 
	 * @param model Le modèle pour passer des données à la vue.
	 * @return Le nom de la vue à afficher.
	 */
	@GetMapping("/sorciers/lister")
	public String afficherTousLesSorciers(Model model) {
		// Ajoute la liste des sorciers au modèle pour l'affichage
		model.addAttribute("sorciersALister", sorcierService.getSorciers());

		// Ajoute la liste des maisons au modèle pour l'affichage
		model.addAttribute("maisonsALister", maisonService.getMaisons());

		// Ajoute un nouvel objet Sorcier au modèle pour le formulaire de création
		model.addAttribute("sorcierACreer", new Sorcier());

		return "liste_sorciers";
	}

	/**
	 * Méthode pour ajouter un nouveau sorcier via le formulaire de soumission.
	 * 
	 * @param sorcier L'objet Sorcier à ajouter.
	 * @return Une redirection vers la méthode de listing pour afficher la liste
	 *         mise à jour.
	 */
	@PostMapping("/sorciers/creer")
	public String ajouterSorcier(@Valid @ModelAttribute("sorcierACreer") Sorcier sorcier, BindingResult binding,
			Model model) {
		if (binding.hasErrors()) {
			// Ajouter la liste des sorciers au modèle pour l'affichage
			model.addAttribute("sorciersALister", sorcierService.getSorciers());

			// Ajouter la liste des maisons au modèle pour l'affichage
			model.addAttribute("maisonsALister", maisonService.getMaisons());

			// Ajouter un nouvel objet Sorcier au modèle pour le formulaire de création
			model.addAttribute("sorcierACreer", new Sorcier());

			return "liste_sorciers";
		}

		// Appeler le service pour ajouter le sorcier.
		sorcierService.ajouterSorcier(sorcier);

		return "redirect:/sorciers/lister";
	}

	/**
	 * Méthode pour afficher les détails d'un sorcier spécifique.
	 * 
	 * @param id    L'identifiant du sorcier à détailler.
	 * @param model Le modèle pour passer des données à la vue.
	 * @return Le nom de la vue à afficher.
	 */
	@GetMapping("/sorciers/detailler")
	public String afficherSorcier(@RequestParam("id") int id, Model model) {
		// Récupérer du sorcier par son identifiant.
		Sorcier sorcierDemande = sorcierService.recupererSorcier(id);

		// Ajouter du sorcier et des maisons au modèle.
		model.addAttribute("sorcierADetailler", sorcierDemande);
		model.addAttribute("maisonsALister", maisonService.getMaisons());

		return "details_sorciers";
	}

	/**
	 * Méthode pour supprimer un sorcier spécifique.
	 * 
	 * @param id L'identifiant du sorcier à supprimer.
	 * @return Une redirection vers la méthode de listing pour afficher la liste
	 *         mise à jour.
	 */
	@GetMapping("/sorciers/supprimer")
	public String supprimerSorcier(@ModelAttribute("id") int id) {

		// Appeler au service pour supprimer le sorcier.
		sorcierService.supprimerSorcier(id);

		return "redirect:/sorciers/lister";
	}

	/**
	 * Méthode pour modifier un sorcier existant via le formulaire de soumission.
	 * 
	 * @param sorcier L'objet Sorcier modifié à enregistrer.
	 * @return Une redirection vers la méthode de listing pour afficher la liste
	 *         mise à jour.
	 */
	@PostMapping("/sorciers/modifier")
	public String modifierSorcier(@Valid @ModelAttribute("sorcier") Sorcier sorcier, BindingResult binding,
			Model model) {
		if (binding.hasErrors()) {
			// Récupérer du sorcier par son identifiant.
			Sorcier sorcierDemande = sorcierService.recupererSorcier(sorcier.getId());

			// Ajouter du sorcier et des maisons au modèle.
			model.addAttribute("sorcierADetailler", sorcierDemande);
			model.addAttribute("maisonsALister", maisonService.getMaisons());

			return "details_sorciers";
		}
		// Appeler au service pour modifier le sorcier.
		sorcierService.modifierSorcier(sorcier);

		return "redirect:/sorciers/lister";
	}

	@PostMapping("/sorciers/rechercher")
	public String rechercherSorciers(@RequestParam("motARechercher") String motARechercher, Model model) {

		List<Sorcier> sorciers = sorcierService.rechercherSorciers(motARechercher);
		model.addAttribute("sorciersALister", sorciers);

		// Ajoute la liste des maisons au modèle pour l'affichage
		model.addAttribute("maisonsALister", maisonService.getMaisons());

		// Ajoute un nouvel objet Sorcier au modèle pour le formulaire de création
		model.addAttribute("sorcierACreer", new Sorcier());

		return "liste_sorciers";
	}
}
