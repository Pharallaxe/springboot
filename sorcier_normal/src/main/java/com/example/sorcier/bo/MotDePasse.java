package com.example.sorcier.bo;

// Valider le format du mot de passe avec une expression régulière.
import jakarta.validation.constraints.Pattern;

/**
 * Classe représentant la gestion des mots de passe pour une entité utilisateur.
 */
public class MotDePasse {
    
    @Pattern(regexp = "^[a-zA-Z0-9]{3,25}$", message = "Le mot de passe doit contenir entre 3 et 25 caractères alphanumériques.")
    private String mdp;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{3,25}$", message = "La confirmation du mot de passe doit contenir entre 3 et 25 caractères alphanumériques.")
    private String confirme;
    
    /**
     * Constructeur avec paramètres pour créer un nouvel objet MotDePasse.
     * @param mdp Le mot de passe de l'utilisateur.
     * @param confirme Le mot de passe confirmé de l'utilisateur.
     */
    public MotDePasse(String mdp, String confirme) {
        this.mdp = mdp;
        this.confirme = confirme;
    }

    /**
     * Constructeur par défaut de la classe MotDePasse.
     */
    public MotDePasse() {
    }

    /**
     * Obtient le mot de passe actuel.
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Définit le nouveau mot de passe de l'utilisateur.
     * @param mdp Le nouveau mot de passe à enregistrer.
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * Obtient le mot de passe confirmé.
     * @return Le mot de passe confirmé de l'utilisateur.
     */
    public String getConfirme() {
        return confirme;
    }

    /**
     * Définit le nouveau mot de passe confirmé de l'utilisateur.
     * @param confirme Le nouveau mot de passe confirmé à enregistrer.
     */
    public void setConfirme(String confirme) {
        this.confirme = confirme;
    }
    
    /**
     * Retourne une chaîne de caractères représentant l'objet MotDePasse.
     * @return Représentation textuelle de l'objet MotDePasse.
     */
    @Override
    public String toString() {
        // Concaténer les valeurs de mdp et confirme dans une chaîne de caractères.
        return "MotDePasse [mdp=" + mdp + ", confirme=" + confirme + "]";
    }

    /**
     * Vérifie si le mot de passe et le mot de passe confirmé sont identiques.
     * @return Vrai si les mots de passe sont identiques, faux sinon.
     */
    public boolean isIdentique() {
        // Comparer le mot de passe et le mot de passe confirmé.
        return this.getMdp().equals(this.getConfirme());
    }
}
