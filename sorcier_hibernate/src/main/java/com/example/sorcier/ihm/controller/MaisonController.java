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
import com.example.sorcier.bo.Maison;

import jakarta.validation.Valid;

/**
 * Contrôleur pour la gestion des maisons dans l'application.
 * Fournit des méthodes pour lister, ajouter, détailler, supprimer, et modifier des maisons.
 */
@Controller
public class MaisonController {
    private MaisonService maisonService;

    /**
     * Constructeur pour l'injection de dépendances du service de gestion des maisons.
     * @param maisonService Le service pour la gestion des maisons.
     */
    public MaisonController(MaisonService maisonService) {
        this.maisonService = maisonService;
    }

    /**
     * Méthode pour lister toutes les maisons disponibles.
     * @param model Le modèle pour passer des données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/maisons/lister")
    public String afficherToutesLesMaisons(Model model) {
    	    	
        // Ajouter la liste des maisons au modèle pour l'affichage
        model.addAttribute("maisonsALister", maisonService.getMaisons());
        
     // Ajoute un nouvel objet Sorcier au modèle pour le formulaire de création
        model.addAttribute("maisonACreer", new Maison()); 
        
        return "liste_maisons";
    }
        
    /**
     * Méthode pour ajouter une nouvelle maison via le formulaire de soumission.
     * @param maison L'objet Maison à ajouter.
     * @return Une redirection vers la méthode de listing pour afficher la liste mise à jour.
     */
    @PostMapping("/maisons/creer")
    public String ajouterMaison(@Valid @ModelAttribute("maison") Maison maison, BindingResult binding, Model model) {
    	if (binding.hasErrors()) {
    		
    		// Ajouter la liste des maisons au modèle pour l'affichage
            model.addAttribute("maisonsALister", maisonService.getMaisons());
            
            // Ajouter un nouvel objet Maison au modèle pour le formulaire de création
            model.addAttribute("maisonACreer", new Maison()); 
            
            return "liste_maisons";
		}
    	// Appel au service pour ajouter la maison
        maisonService.ajouterMaison(maison);
        return "redirect:/maisons/lister";
    }
    
    /**
     * Méthode pour afficher les détails d'une maison spécifique.
     * @param id L'identifiant de la maison à détailler.
     * @param model Le modèle pour passer des données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/maisons/detailler")
    public String afficherMaison(@RequestParam("id") int id_maison, Model model) {
        // Récupération de la maison par son identifiant.
        Maison maisonDemandee = maisonService.recupererMaison(id_maison);
        
        // Ajout de la maison au modèle pour l'affichage.
        model.addAttribute("maisonsADetailler", maisonDemandee);
        
        return "details_maisons";
    }
    
    /**
     * Méthode pour supprimer une maison spécifique.
     * @param id L'identifiant de la maison à supprimer.
     * @return Une redirection vers la méthode de listing pour afficher la liste mise à jour.
     */
    @GetMapping("/maisons/supprimer")
    public String supprimerMaison(@RequestParam("id") int id_maisonASupprimer) {
        maisonService.supprimerMaison(id_maisonASupprimer);
        return "redirect:/maisons/lister";
    }
    
    /**
     * Méthode pour modifier une maison existante via le formulaire de soumission.
     * @param id L'identifiant de la maison à modifier.
     * @param m L'objet Maison contenant les nouvelles informations.
     * @return Une redirection vers la méthode de listing pour afficher la liste mise à jour.
     */
    @PostMapping("/maisons/modifier")
    public String modifierMaison(@Valid @ModelAttribute("maison") Maison maison, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
        	// Récupération de la maison par son identifiant.
            Maison maisonDemandee = maisonService.recupererMaison(maison.getId());
            
            // Ajout de la maison au modèle pour l'affichage.
            model.addAttribute("maisonsADetailler", maisonDemandee);
            
            return "details_maisons";
        }
        // Appel au service pour modifier la maison.
        maisonService.modifierMaison(maison);    
        
        return "redirect:/maisons/lister";
    }
    
    @PostMapping("/maisons/rechercher")
    public String rechercherMaisons(@RequestParam("motARechercher") String motARechercher, Model model) {
    	
        List<Maison> maisons = maisonService.rechercherMaisons(motARechercher);
        
        model.addAttribute("maisonsALister", maisons);
        // Ajouter un nouvel objet Maison au modèle pour le formulaire de création
        model.addAttribute("maisonACreer", new Maison()); 
        
        return "liste_maisons";
    }
}
