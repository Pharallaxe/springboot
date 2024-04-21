package com.example.sorcier.ihm.controller;

import java.util.List;

// Importe les annotations pour définir les contrôleurs dans Spring MVC.
import org.springframework.stereotype.Controller;
// Importe les classes pour la gestion des modèles dans les vues.
import org.springframework.ui.Model;
// Importe les classes pour gérer les résultats de la validation.
import org.springframework.validation.BindingResult;
// Importe les annotations pour la gestion des requêtes GET et POST.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Importe le service de logique métier pour la gestion des maisons.
import com.example.sorcier.bll.MaisonService;
// Importe l'objet de transfert de données Maison.
import com.example.sorcier.bo.Maison;

// Importe l'annotation pour la validation des modèles.
import jakarta.validation.Valid;

/**
 * Contrôleur Spring MVC pour gérer les opérations liées aux maisons.
 */
@Controller
public class MaisonController {
    private MaisonService maisonService;

    /**
     * Constructeur pour initialiser le service de maisons.
     * 
     * @param maisonService - Le service de gestion des maisons.
     */
    public MaisonController(MaisonService maisonService) {
        // Initialise le service de gestion des maisons.
        this.maisonService = maisonService;
    }

    /**
     * Méthode GET pour afficher la liste de toutes les maisons.
     * 
     * @param model - Le Modèle Spring pour passer des données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/maisons/lister")
    public String afficherToutesLesMaisons(Model model) {
        // Ajoute la liste des maisons au modèle pour l'affichage.
        model.addAttribute("maisonsALister", maisonService.recupererMaisons());
        
        // Ajoute un nouvel objet Maison au modèle pour un éventuel enregistrement.
        model.addAttribute("maisonACreer", new Maison()); 
        
        // Renvoie le nom de la vue qui affiche la liste des maisons.
        return "liste_maisons";
    }

    /**
     * Méthode POST pour créer une nouvelle maison.
     * 
     * @param maison - L'objet Maison à créer, validé automatiquement par Spring MVC.
     * @param binding - Le résultat des validations.
     * @param model - Le Modèle Spring MVC.
     * @return La redirection vers la liste des maisons.
     */
    @PostMapping("/admin/maisons/creer")
    public String ajouterMaison(@Valid @ModelAttribute("maison") Maison maison, BindingResult binding, Model model) {
        // Vérifie si des erreurs de validation sont présentes.
        if (binding.hasErrors()) {
        	
            // Ajoute la liste des maisons au modèle pour la correction.
            model.addAttribute("maisonsALister", maisonService.recupererMaisons());
            
            // Ajoute un nouvel objet Maison pour la correction des erreurs.
            model.addAttribute("maisonACreer", new Maison()); 
            
            // Renvoye à la vue de la liste des maisons en cas d'erreur.
            return "liste_maisons";
        }
        // Ajoute la nouvelle maison via le service si pas d'erreurs.
        maisonService.ajouterMaison(maison);
        
        // Redirige vers la liste des maisons après l'ajout.
        return "redirect:/maisons/lister";
    }

    /**
     * Méthode GET pour afficher les détails d'une maison spécifique.
     * 
     * @param id_maison - L'id de la maison à afficher.
     * @param model - Le Modèle Spring MVC.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/maisons/detailler")
    public String afficherMaison(@RequestParam("id") int id_maison, Model model) {
        // Récupére la maison demandée via le service.
        Maison maisonDemandee = maisonService.recupererMaison(id_maison);      
        
        // Ajoute la maison récupérée au modèle pour l'affichage.
        model.addAttribute("maisonsADetailler", maisonDemandee);
        
        // Renvoie le nom de la vue qui affiche les détails de la maison.
        return "details_maisons";
    }

    /**
     * Méthode GET pour supprimer une maison.
     * 
     * @param id_maisonASupprimer - L'identifiant de la maison à supprimer.
     * @return La redirection vers la liste des maisons.
     */
    @GetMapping("/admin/maisons/supprimer")
    public String supprimerMaison(@ModelAttribute("id") int id_maisonASupprimer) {
        // Supprime la maison via le service.
        maisonService.supprimerMaison(id_maisonASupprimer);
        
        // Redirige vers la liste des maisons après suppression.
        return "redirect:/maisons/lister";
    }

    /**
     * Méthode POST pour modifier une maison existante.
     * 
     * @param maison - L'objet Maison modifié, validé automatiquement par Spring MVC.
     * @param binding - Le résultat des validations.
     * @param model - Le modèle Spring MVC.
     * 
     * @return Redirection vers la liste des maisons ou vue de détail en cas d'erreur.
     */
    @PostMapping("/admin/maisons/modifier")
    public String modifierMaison(@Valid @ModelAttribute("maison") Maison maison, BindingResult binding, Model model) {
        // Vérifie si des erreurs de validation sont présentes.
        if (binding.hasErrors()) {
        	
            // Récupére la maison actuelle pour afficher les informations correctes.
            Maison maisonDemandee = maisonService.recupererMaison(maison.getId());
            
            // Ajoute la maison actuelle au modèle pour correction.
            model.addAttribute("maisonsADetailler", maisonDemandee);
            
            // Renvoie à la vue des détails de la maison en cas d'erreur.
            return "details_maisons";
        }
        // Modifie la maison via le service si pas d'erreurs.
        maisonService.modifierMaison(maison);    
        
        // Redirige vers la liste des maisons après modification.
        return "redirect:/maisons/lister";
    }

    /**
     * Méthode POST pour rechercher des maisons par un mot clé.
     * 
     * @param motARechercher - Le mot clé pour la recherche.
     * @param model - Le Modèle Spring.
     * @return Le nom de la vue à afficher avec les résultats.
     */
    @PostMapping("/maisons/rechercher")
    public String rechercherMaisons(@RequestParam("motARechercher") String motARechercher, Model model) {
        // Effectue la recherche des maisons via le service avec le mot clé fourni.
        List<Maison> maisons = maisonService.rechercherMaisons(motARechercher);
        
        // Ajoute les maisons trouvées au modèle pour l'affichage.
        model.addAttribute("maisonsALister", maisons);
        
        // Ajoute un nouvel objet Maison au modèle pour un éventuel enregistrement.
        model.addAttribute("maisonACreer", new Maison()); 
        
        // Renvoie le nom de la vue qui affiche la liste des maisons avec les résultats de recherche.
        return "liste_maisons";
    }
}
