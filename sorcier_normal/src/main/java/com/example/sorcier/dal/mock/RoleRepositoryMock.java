package com.example.sorcier.dal.mock;

import java.util.ArrayList;
import java.util.List;

// Annotations Spring pour définir le profil et la nature de la classe.
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.example.sorcier.bo.Role;
import com.example.sorcier.dal.RoleRepository;

/**
 * Implémentation de simulation pour le repository de Role, utilisant une liste en mémoire.
 */
@Repository
@Profile("dev")
public class RoleRepositoryMock implements RoleRepository {
    private List<Role> roles = new ArrayList<>();
    private int idIndex;
    
    /**
     * Constructeur pour initialiser le repository avec les données existantes.
     */
    public RoleRepositoryMock() {
        // Récupère l'instance singleton pour accéder aux données simulées.
        SingletonRepositoryMock singletonRepositoryMock = SingletonRepositoryMock.getInstance();
        // Initialise la liste des rôles avec les données du singleton.
        roles = singletonRepositoryMock.getRoles();
        // Définit l'index de l'ID en se basant sur la taille de la liste des rôles.
        idIndex = roles.size() + 1;
    }

    /**
     * Ajoute un nouveau rôle à la liste.
     * @param id_utilisateur L'identifiant de l'utilisateur auquel le rôle est attribué.
     * @param nomRole Le nom du rôle à ajouter.
     */
    @Override
    public void save(int id_utilisateur, String nomRole) {
        // Crée un nouvel objet Role avec un ID unique.
        Role role = new Role(idIndex, id_utilisateur, nomRole);
        // Incrémente l'index pour le prochain rôle à ajouter.
        idIndex++;
        // Ajoute le nouveau rôle à la liste.
        roles.add(role);
        // Affiche la liste des rôles pour le débogage.
        System.out.println(roles);
        // Affiche les détails du nouveau rôle ajouté pour le débogage.
        System.out.println(role);
    }

    /**
     * Supprime les rôles associés à un utilisateur spécifique.
     * @param id_utilisateur L'identifiant de l'utilisateur dont les rôles doivent être supprimés.
     */
    @Override
    public void remove(int id_utilisateur) {
        // Supprime tous les rôles où l'identifiant de l'utilisateur correspond.
        roles.removeIf(r -> r.getIdUtilisateur() == id_utilisateur);
    }
}
