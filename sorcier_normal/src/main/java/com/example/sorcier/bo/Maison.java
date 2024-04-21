package com.example.sorcier.bo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

/**
 * Classe représentant une maison dans un univers de sorciers.
 */
public class Maison {

    protected int id;

    @Pattern(regexp = "^[a-zA-Z]{4,20}$", message = "Le nom doit contenir entre 4 et 20 lettres alphabetiques.")
    protected String nom;

    @Min(value = 0, message = "Le bonus d'attaque doit être au moins de 0")
    @Max(value = 8, message = "Le bonus d'attaque ne peut pas dépasser 8")
    protected int bonusAttaque;

    @Min(value = 0, message = "Le bonus de santé doit être au moins de 0")
    @Max(value = 30, message = "Le bonus de santé ne peut pas dépasser 30")
    protected int bonusSante;

    protected boolean modifiable = true;

    /**
     * Constructeur par défaut de la classe Maison.
     */
    public Maison() {
    }

    /**
     * Constructeur complet de la classe Maison.
     * 
     * @param id - Identifiant unique de la maison.
     * @param nom - Nom de la maison.
     * @param bonusAttaque - Bonus d'attaque de la maison.
     * @param bonusSante - Bonus de santé de la maison.
     * @param modifiable - Indique si la maison est modifiable ou non.
     */
    public Maison(int id, String nom, int bonusAttaque, int bonusSante, boolean modifiable) {
        this.id = id;
        this.nom = nom;
        this.bonusAttaque = bonusAttaque;
        this.bonusSante = bonusSante;
        this.modifiable = modifiable;
    }

    /**
     * Constructeur de la classe Maison sans ID.
     * @param nom Nom de la maison.
     * @param bonusAttaque Bonus d'attaque de la maison.
     * @param bonusSante Bonus de santé de la maison.
     * @param modifiable Indique si la maison est modifiable ou non.
     */
    public Maison(String nom, int bonusAttaque, int bonusSante, boolean modifiable) {
        this.nom = nom;
        this.bonusAttaque = bonusAttaque;
        this.bonusSante = bonusSante;
        this.modifiable = modifiable;
    }

    /**
     * Obtient l'identifiant unique de la maison.
     * @return Identifiant de la maison.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de la maison.
     * @param id Nouvel identifiant de la maison.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom de la maison.
     * @return Nom de la maison.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la maison.
     * @param nom Nouveau nom de la maison.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le bonus d'attaque de la maison.
     * @return Bonus d'attaque de la maison.
     */
    public int getBonusAttaque() {
        return bonusAttaque;
    }

    /**
     * Définit le bonus d'attaque de la maison.
     * @param bonusAttaque Nouveau bonus d'attaque.
     */
    public void setBonusAttaque(int bonusAttaque) {
        this.bonusAttaque = bonusAttaque;
    }

    /**
     * Obtient le bonus de santé de la maison.
     * @return Bonus de santé de la maison.
     */
    public int getBonusSante() {
        return bonusSante;
    }

    /**
     * Définit le bonus de santé de la maison.
     * @param bonusSante Nouveau bonus de santé.
     */
    public void setBonusSante(int bonusSante) {
        this.bonusSante = bonusSante;
    }

    /**
     * Vérifie si la maison est modifiable.
     * @return Vrai si la maison est modifiable, faux sinon.
     */
    public boolean isModifiable() {
        return modifiable;
    }

    /**
     * Définit si la maison est modifiable.
     * @param modifiable Nouvel état modifiable de la maison.
     */
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    /**
     * Retourne une chaîne de caractères représentant la maison.
     * @return Représentation textuelle de la maison.
     */
    @Override
    public String toString() {
        // Concaténer les propriétés de la maison dans une chaîne.
        return "Maison [id=" + id + ", nom=" + nom + ", bonusAttaque=" + bonusAttaque + ", bonusSante=" + bonusSante
                + ", modifiable=" + modifiable + "]";
    }
}
