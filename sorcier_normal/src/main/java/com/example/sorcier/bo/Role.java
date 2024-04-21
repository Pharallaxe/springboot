package com.example.sorcier.bo;

// Importe les annotations de validation pour vérifier le format du rôle.
import jakarta.validation.constraints.Pattern;

/**
 * Classe représentant un rôle attribué à un utilisateur dans un système.
 */
public class Role {
    private int id;
    private int idUtilisateur;
    
    @Pattern(regexp = "^(ADMIN|INSCRIT)$", message = "Le rôle doit être 'ADMIN' ou 'INSCRIT'.")
    private String role;

    /**
     * Constructeur par défaut de la classe Role.
     */
    public Role() {
        // Initialiser un objet Role sans paramètres.
    }

    /**
     * Constructeur complet de la classe Role.
     * @param id Identifiant unique du rôle.
     * @param idUtilisateur Identifiant de l'utilisateur associé à ce rôle.
     * @param role Nom du rôle.
     */
    public Role(int id, int idUtilisateur, String role) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.role = role;
    }

    /**
     * Constructeur de la classe Role sans l'identifiant du rôle.
     * @param idUtilisateur Identifiant de l'utilisateur associé à ce rôle.
     * @param role Nom du rôle.
     */
    public Role(int idUtilisateur, String role) {
        this.idUtilisateur = idUtilisateur;
        this.role = role;
    }

    /**
     * Obtient l'identifiant unique du rôle.
     * @return Identifiant du rôle.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du rôle.
     * @param id Nouvel identifiant du rôle.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient l'identifiant de l'utilisateur associé au rôle.
     * @return Identifiant de l'utilisateur.
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Définit l'identifiant de l'utilisateur associé au rôle.
     * @param idUtilisateur Nouvel identifiant de l'utilisateur.
     */
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    /**
     * Obtient le rôle attribué à l'utilisateur.
     * @return Rôle de l'utilisateur.
     */
    public String getRole() {
        return role;
    }

    /**
     * Définit le rôle attribué à l'utilisateur.
     * @param role Nouveau rôle à attribuer.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retourne une chaîne de caractères représentant l'objet Role.
     * @return Représentation textuelle de l'objet Role.
     */
    @Override
    public String toString() {
        // Concaténer les propriétés de l'objet Role dans une chaîne de caractères.
        return "Role [id=" + id + ", idUtilisateur=" + idUtilisateur + ", role=" + role + "]";
    }
}
