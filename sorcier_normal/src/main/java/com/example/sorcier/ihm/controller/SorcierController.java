package com.example.sorcier.ihm.controller;

import java.util.List;

// Importe les annotations pour définir les contrôleurs dans Spring MVC.
import org.springframework.stereotype.Controller;
// Importe les classes pour la gestion des modèles dans les vues.
import org.springframework.ui.Model;
// Importe les classes pour gérer les résultats de la validation.
import org.springframework.validation.BindingResult;
// Importe les annotations pour la gestion des requêtes GET.
import org.springframework.web.bind.annotation.GetMapping;
// Importe les annotations pour la gestion des requêtes POST.
import org.springframework.web.bind.annotation.PostMapping;
// Importe les annotations pour la gestion des paramètres de requête et des attributs de modèle.
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

// Importe les services de logique métier pour la gestion des maisons et des sorciers.
import com.example.sorcier.bll.MaisonService;
import com.example.sorcier.bll.SorcierService;
// Importe l'objet de transfert de données Sorcier.
import com.example.sorcier.bo.Sorcier;

// Importe l'annotation pour la validation des modèles.
import jakarta.validation.Valid;

/**
 * Contrôleur Spring pour gérer les opérations liées aux sorciers.
 */
@Controller
public class SorcierController {
    private SorcierService sorcierService;
    private MaisonService maisonService;
    
    /**
     * Constructeur pour initialiser les services de sorciers et de maisons.
     * 
     * @param sorcierService - Le service de gestion des sorciers.
     * @param maisonService - Le service de gestion des maisons.
     */
    public SorcierController(SorcierService sorcierService, MaisonService maisonService) {
        // Initialise le service de gestion des sorciers.
        this.sorcierService = sorcierService;
        
        // Initialise du service de gestion des maisons.
        this.maisonService = maisonService;
    }

    /**
     * Méthode GET pour afficher la liste de tous les sorciers.
     * 
     * @param model - Le odèle Spring pour passer des données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/sorciers/lister")
    public String afficherTousLesSorciers(Model model) {
        // Ajoute la liste des sorciers au modèle pour l'affichage.
        model.addAttribute("sorciersALister", sorcierService.recupererSorciers());
        
        // Ajoute la liste des maisons au modèle.
        model.addAttribute("maisonsALister", maisonService.recupererMaisons());
        
        // Ajoute un nouvel objet Sorcier au modèle pour un éventuel enregistrement.
        model.addAttribute("sorcierACreer", new Sorcier()); 
        
        // Renvoie le nom de la vue qui affiche la liste des sorciers.
        return "liste_sorciers";
    }

    /**
     * Méthode POST pour créer un nouveau sorcier.
     * 
     * @param sorcier - L'objet Sorcier à créer, validé automatiquement par Spring.
     * @param binding - Le résultat des validations.
     * @param model - le modèle Spring.
     * @param avecMaison - Le booléen pour indiquer si le sorcier doit être associé à une maison lors de la création.
     * @return La redirection vers la liste des sorciers.
     */
    @PostMapping("/admin/sorciers/creer")
    public String ajouterSorcier(
    		@Valid @ModelAttribute("sorcierACreer") Sorcier sorcier,
    		BindingResult binding,
    		Model model,
    		@RequestParam(value = "avecMaison", defaultValue = "false") boolean avecMaison
    ) {
        // Vérifie si des erreurs de validation sont présentes.
        if (binding.hasErrors()) {
        	
            // Redirige vers la liste des sorciers en cas d'erreur.
        	return "redirect:/sorciers/lister";
        }
        // Vérifie si le sorcier doit être associé à une maison.
        if (avecMaison) {
        	
            // Ajoute le sorcier avec une maison via le service.
            sorcierService.ajouterSorcierMaison(sorcier);
        } else {
            // Ajoute le sorcier sans maison via le service.
            sorcierService.ajouterSorcier(sorcier);
        }
        // Redirige vers la liste des sorciers après l'ajout.
        return "redirect:/sorciers/lister";
    }

    /**
     * Méthode GET pour afficher les détails d'un sorcier spécifique.
     * 
     * @param id - L'id du sorcier à afficher.
     * @param model - Le modèle Spring.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/sorciers/detailler")
    public String afficherSorcier(@RequestParam("id") int id, Model model) {
        // Récupére le sorcier demandé via le service.
        Sorcier sorcierDemande = sorcierService.recupererSorcier(id);
        
        // Ajoute le sorcier récupéré au modèle pour l'affichage.
        model.addAttribute("sorcierADetailler", sorcierDemande);
        
        // Ajoute la liste des maisons au modèle.
        model.addAttribute("maisonsALister", maisonService.recupererMaisons());
        
        // Renvoie le nom de la vue qui affiche les détails du sorcier.
        return "details_sorciers";
    }

    /**
     * Méthode GET pour supprimer un sorcier spécifique.
     * 
     * @param id - L'id du sorcier à supprimer.
     * @return La redirection vers la liste des sorciers après suppression.
     */
    @GetMapping("/admin/sorciers/supprimer")
    public String supprimerSorcier(@ModelAttribute("id") int id) {
        // Supprime le sorcier spécifié par l'ID.
        sorcierService.supprimerSorcier(id);
        
        // Redirige vers la liste des sorciers après la suppression.
        return "redirect:/sorciers/lister";
    }

    /**
     * Méthode POST pour modifier les détails d'un sorcier.
     * 
     * @param sorcier - L'objet Sorcier contenant les modifications.
     * @param binding - Le résultat des validations.
     * @param model - Le modèle pour passer des données à la vue.
     * @return La redirection vers la liste des sorciers ou retour à la vue de détail en cas d'erreur.
     */
    @PostMapping("/admin/sorciers/modifier")
    public String modifierSorcier(@Valid @ModelAttribute("sorcier") Sorcier sorcier, BindingResult binding, Model model) {
        // Vérifie si des erreurs de validation sont présentes.
        if (binding.hasErrors()) {
        	
            // Redirige vers la vue de détail du sorcier en cas d'erreur.
            return "redirect:/sorciers/detailler";
        }
        // Applique les modifications au sorcier via le service.
        sorcierService.modifierSorcier(sorcier);
        
        // Redirige vers la liste des sorciers après la modification.
        return "redirect:/sorciers/lister";
    }

    /**
     * Méthode POST pour rechercher des sorciers en fonction d'un mot clé.
     * 
     * @param motARechercher - Le mot clé utilisé pour la recherche.
     * @param model - Le modèle pour passer des données à la vue.
     * @return Vue de la liste des sorciers avec les résultats de la recherche.
     */
    @PostMapping("/sorciers/rechercher")
    public String rechercherSorciers(@RequestParam("motARechercher") String motARechercher, Model model) {
        // Recherche des sorciers utilisant le mot clé fourni.
        List<Sorcier> sorciers = sorcierService.rechercherSorciers(motARechercher);
        
        // Ajoute la liste des sorciers trouvés au modèle.
        model.addAttribute("sorciersALister", sorciers);
        
        // Ajoute la liste des maisons au modèle pour affichage.
        model.addAttribute("maisonsALister", maisonService.recupererMaisons());
        
        // Ajoute un nouvel objet Sorcier au modèle pour un éventuel enregistrement.
        model.addAttribute("sorcierACreer", new Sorcier()); 
        
        // Renvoie le nom de la vue qui affiche la liste des sorciers avec les résultats de recherche.
        return "liste_sorciers";
    }
}
