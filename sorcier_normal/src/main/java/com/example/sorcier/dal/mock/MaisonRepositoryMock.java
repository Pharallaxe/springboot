package com.example.sorcier.dal.mock;

// Importe pour gérer les listes en Java.
import java.util.ArrayList;
import java.util.List;

// Annotations Spring pour définir le profil et la nature de la classe.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

// Importe les classes métier.
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
// Importe l'interface de la couche d'accès aux données pour les maisons.
import com.example.sorcier.dal.MaisonRepository;

/**
 * Implémentation de simulation pour le repository de Maison, utilisant une liste en mémoire.
 */
@Repository
@Profile("dev")  // Ce repository est utilisé uniquement pour le profil de développement.
public class MaisonRepositoryMock implements MaisonRepository {
    private List<Maison> maisons = new ArrayList<>();
    private List<Sorcier> sorciers = new ArrayList<>();
    private int idIndex;
    
    /**
     * Constructeur pour initialiser le repository avec les données existantes.
     */
    public MaisonRepositoryMock() {
        // Récupère l'instance singleton pour accéder aux données simulées.
        SingletonRepositoryMock singletonRepositoryMock = SingletonRepositoryMock.getInstance();
        // Initialise la liste des maisons avec les données du singleton.
        maisons = singletonRepositoryMock.getMaisons();
        // Initialise la liste des sorciers avec les données du singleton.
        sorciers = singletonRepositoryMock.getSorciers();
        // Définit l'index de l'ID en se basant sur la taille de la liste des maisons.
        idIndex = maisons.size() + 1;
    }

    /**
     * Retourne toutes les maisons stockées.
     * @return Liste de toutes les maisons.
     */
    @Override
    public List<Maison> findAll() {
        return maisons;  // Retourne la liste complète des maisons.
    }

    /**
     * Trouve une maison par son ID.
     * @param id L'ID de la maison à trouver.
     * @return La maison correspondante ou null si non trouvée.
     */
    @Override
    public Maison findById(int id) {
        // Utilise un flux pour filtrer et trouver la première maison correspondant à l'ID donné.
        return maisons.stream()
                      .filter(m -> m.getId() == id)
                      .findFirst()
                      .orElse(null);
    }

    /**
     * Trouve une maison par son nom.
     * @param maison Maison dont le nom est utilisé pour la recherche.
     * @return La maison correspondante ou null si non trouvée.
     */
    @Override
    public Maison findByName(Maison maison) {
        // Utilise un flux pour filtrer et trouver la première maison correspondant au nom donné.
        return maisons.stream()
                      .filter(m -> m.getNom().equals(maison.getNom()))
                      .findFirst()
                      .orElse(null);
    }
    
    /**
     * Supprime une maison par son ID, si aucun sorcier n'est lié à cette maison.
     * @param id L'ID de la maison à supprimer.
     */
    @Override
    public void removeById(int id) {
        // Compte le nombre de sorciers liés à cette maison.
        long sorciersRestant = sorciers.stream()
                                       .filter(s -> s.getMaison().getId() == id)
                                       .count();
        // Supprime la maison uniquement si aucun sorcier n'est lié.
        if (sorciersRestant == 0) {
            maisons.removeIf(m -> m.getId() == id);
        } else {
            System.out.println("Suppression impossible: des sorciers sont encore liés à cette maison.");
        }
    }

    /**
     * Ajoute une nouvelle maison à la liste.
     * @param maison La nouvelle maison à ajouter.
     */
    @Override
    public void save(Maison maison) {
        // Attribue un nouvel ID à la maison et l'ajoute à la liste.
        maison.setId(idIndex++);
        maisons.add(maison);
    }

    /**
     * Met à jour les informations d'une maison existante.
     * @param maison Les nouvelles informations de la maison.
     */
    @Override
    public void update(Maison maison) {
        // Trouve la maison existante par son ID.
        Maison maisonAModifier = findById(maison.getId());
        if (maisonAModifier != null) {
            // Met à jour les propriétés de la maison.
            maisonAModifier.setNom(maison.getNom());
            maisonAModifier.setBonusAttaque(maison.getBonusAttaque());
            maisonAModifier.setBonusSante(maison.getBonusSante());
            System.out.println("Maison modifiée : " + maisonAModifier.getId());
        } else {
            System.out.println("Maison non trouvée pour l'ID : " + maison.getId());
        }
    }
}
