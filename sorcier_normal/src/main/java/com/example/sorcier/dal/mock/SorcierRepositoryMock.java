package com.example.sorcier.dal.mock;

// Utilisé pour la gestion des listes en Java.
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Annotations Spring pour la configuration du repository.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

// Importe la classe métier Sorcier.
import com.example.sorcier.bo.Sorcier;
// Importe l'interface de la couche d'accès aux données pour les sorciers.
import com.example.sorcier.dal.SorcierRepository;

/**
 * Implémentation de simulation pour le repository de Sorcier, utilisant une liste en mémoire.
 */
@Repository
@Profile("dev") // Ce repository est utilisé uniquement pour le profil de développement.
public class SorcierRepositoryMock implements SorcierRepository {
    private List<Sorcier> sorciers = new ArrayList<>();
    private int idIndex;

    /**
     * Constructeur pour initialiser le repository avec les données existantes.
     */
    public SorcierRepositoryMock() {
        // Récupère l'instance singleton pour accéder aux données simulées.
        SingletonRepositoryMock singletonRepositoryMock = SingletonRepositoryMock.getInstance();
        // Initialise la liste des sorciers avec les données du singleton.
        sorciers = singletonRepositoryMock.getSorciers();
        // Définit l'index de l'ID en se basant sur la taille de la liste des sorciers.
        idIndex = sorciers.size() + 1;
    }

    /**
     * Retourne tous les sorciers stockés.
     * @return Liste de tous les sorciers.
     */
    @Override
    public List<Sorcier> findAll() {
        return sorciers; // Retourne la liste complète des sorciers.
    }

    /**
     * Trouve un sorcier par son ID.
     * @param id L'ID du sorcier à trouver.
     * @return Le sorcier correspondant ou null si non trouvé.
     */
    @Override
    public Sorcier findById(int id) {
        return sorciers.stream()
                       .filter(s -> s.getId() == id)
                       .findFirst()
                       .orElse(null); // Cherche et retourne le sorcier par son ID ou null si non trouvé.
    }

    /**
     * Retourne tous les sorciers d'une maison spécifique.
     * @param id_maison L'ID de la maison dont les sorciers sont recherchés.
     * @return Liste des sorciers de cette maison.
     */
    @Override
    public List<Sorcier> findByMaison(int id_maison) {
        return sorciers.stream()
                       .filter(s -> s.getMaison().getId() == id_maison)
                       .collect(Collectors.toList()); // Collecte et retourne les sorciers de la maison spécifiée.
    }

    /**
     * Trouve un sorcier par son nom et son prénom.
     * @param sorcier Le sorcier dont les noms sont utilisés pour la recherche.
     * @return Le sorcier correspondant ou null si non trouvé.
     */
    @Override
    public Sorcier findByName(Sorcier sorcier) {
        return sorciers.stream()
                       .filter(s -> s.getNom().equals(sorcier.getNom()) && s.getPrenom().equals(sorcier.getPrenom()))
                       .findFirst()
                       .orElse(null); // Cherche et retourne le sorcier par son nom et prénom ou null si non trouvé.
    }

    /**
     * Supprime un sorcier par son ID.
     * @param id L'ID du sorcier à supprimer.
     */
    @Override
    public void removeById(int id) {
        sorciers.removeIf(s -> s.getId() == id); // Supprime le sorcier correspondant à l'ID donné.
    }

    /**
     * Ajoute un nouveau sorcier à la liste.
     * @param sorcier Le nouveau sorcier à ajouter.
     */
    @Override
    public void save(Sorcier sorcier) {
        sorcier.setId(idIndex); // Attribue un nouvel ID au sorcier.
        idIndex++; // Incrémente l'index pour le prochain sorcier.
        sorciers.add(sorcier); // Ajoute le sorcier à la liste.
    }

    /**
     * Met à jour les informations d'un sorcier existant.
     * @param sorcier Le sorcier avec les informations mises à jour.
     */
    @Override
    public void update(Sorcier sorcier) {
        Sorcier sorcierAModifier = findById(sorcier.getId()); // Trouve le sorcier existant par son ID.
        if (sorcierAModifier != null) {
            // Met à jour les propriétés du sorcier.
            sorcierAModifier.setNom(sorcier.getNom());
            sorcierAModifier.setPrenom(sorcier.getPrenom());
            sorcierAModifier.setMaison(sorcier.getMaison());
            sorcierAModifier.setAttaque(sorcier.getAttaque());
            sorcierAModifier.setSante(sorcier.getSante());
            System.out.println("SRM : Sorcier modifié : " + sorcierAModifier.getId());
        } else {
            System.out.println("SRM : Sorcier non trouvé pour l'ID : " + sorcier.getId());
        }
    }
}
