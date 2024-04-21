package com.example.sorcier.dal.mock;

import java.util.ArrayList;
import java.util.List;

// Annotations Spring pour la configuration du repository.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.dal.UtilisateurRepository;

/**
 * Implémentation de simulation pour le repository de Utilisateur, utilisant une liste en mémoire.
 */
@Repository
@Profile("dev")
public class UtilisateurRepositoryMock implements UtilisateurRepository {
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private int idIndex;

    /**
     * Constructeur pour initialiser le repository avec les données existantes.
     */
    public UtilisateurRepositoryMock() {
        SingletonRepositoryMock singletonRepositoryMock = SingletonRepositoryMock.getInstance();
        utilisateurs = singletonRepositoryMock.getUtilisateurs();
        idIndex = utilisateurs.size() + 1;
    }

    /**
     * Trouve un utilisateur par son ID.
     * @param id L'ID de l'utilisateur à trouver.
     * @return L'utilisateur correspondant ou null si non trouvé.
     */
    @Override
    public Utilisateur findById(int id) {
        // Utilise un flux pour filtrer et trouver le premier utilisateur correspondant à l'ID donné.
        return utilisateurs.stream()
                           .filter(u -> u.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    /**
     * Trouve un utilisateur par son nom complet.
     * @param utilisateur L'utilisateur dont le nom complet est utilisé pour la recherche.
     * @return L'utilisateur correspondant ou null si non trouvé.
     */
    @Override
    public Utilisateur findByName(Utilisateur utilisateur) {
        // Utilise un flux pour filtrer et trouver le premier utilisateur correspondant au nom complet donné.
        return utilisateurs.stream()
                           .filter(u -> u.getNom().equals(utilisateur.getNom()))
                           .findFirst()
                           .orElse(null);
    }

    /**
     * Supprime un utilisateur par son ID.
     * @param id L'ID de l'utilisateur à supprimer.
     */
    @Override
    public void removeById(int id) {
        // Supprime l'utilisateur correspondant à l'ID donné.
        utilisateurs.removeIf(u -> u.getId() == id);
    }

    /**
     * Ajoute un nouvel utilisateur à la liste.
     * @param utilisateur Le nouvel utilisateur à ajouter.
     */
    @Override
    public void save(Utilisateur utilisateur) {
        // Attribue un nouvel ID à l'utilisateur.
        utilisateur.setId(idIndex);
        
        // Incrémente l'index pour le prochain utilisateur.
        idIndex++;
        
        // Ajoute l'utilisateur à la liste.
        utilisateurs.add(utilisateur);
        
        // Affiche l'utilisateur ajouté pour le débogage.
        System.out.println(utilisateur);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     * @param utilisateur L'utilisateur avec les informations mises à jour.
     */
    @Override
    public void update(Utilisateur utilisateur) {
        // Trouve l'utilisateur existant par son ID.
        Utilisateur utilisateurAModifier = findById(utilisateur.getId());
        
        if (utilisateurAModifier != null) {
            // Met à jour les propriétés de l'utilisateur.
            utilisateurAModifier.setSorcier(utilisateur.getSorcier());
            
            // Affiche un message confirmant la modification.
            System.out.println("URM : Utilisateur modifiée : " + utilisateurAModifier.getId());
            
        } else {
            // Affiche un message si l'utilisateur n'est pas trouvé.
            System.out.println("Utilisateur non trouvé pour l'ID : " + utilisateur.getId());
        }
    }

    /**
     * Trouve un utilisateur par son nom.
     * @param nom Le nom de l'utilisateur à trouver.
     * @return L'utilisateur correspondant ou null si non trouvé.
     */
    public Utilisateur findByName(String nom) {
        // Utilise un flux pour filtrer et trouver le premier utilisateur correspondant au nom donné.
        return utilisateurs.stream()
                           .filter(u -> u.getNom().equals(nom))
                           .findFirst()
                           .orElse(null);
    }
}
