package com.example.sorcier.bo;

// Importe l'annotation pour la validation des patterns des chaînes de caractères.
import jakarta.validation.constraints.Pattern;
// Importe les annotations pour définir les valeurs minimales et maximales d'un entier.
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
// Importe l'annotation pour la validation d'objets imbriqués.
import jakarta.validation.Valid;

/**
 * Classe représentant un sorcier avec des caractéristiques spécifiques et une maison associée.
 */
public class Sorcier {
    
    protected int id;
    
    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "Le nom doit être composé de 3 à 25 lettres.")
    protected String nom;
    
    @Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "Le prénom doit être composé de 3 à 25 lettres.")
    protected String prenom;
    
    @Min(value = 2, message = "L'attaque doit être au moins de 2")
    @Max(value = 18, message = "L'attaque ne peut pas dépasser 18")
    protected int attaque;
    
    @Min(value = 40, message = "La santé doit être au moins de 40")
    @Max(value = 300, message = "La santé ne peut pas dépasser 300")
    protected int sante;
    
    protected int maxSante = sante;
    
    protected boolean modifiable = true;
    
    @Valid
    protected Maison maison;

    /**
     * Constructeur par défaut de la classe Sorcier.
     */
    public Sorcier() {
    }

    /**
     * Constructeur complet de la classe Sorcier.
     * @param id Identifiant unique du sorcier.
     * @param nom Nom du sorcier.
     * @param prenom Prénom du sorcier.
     * @param maison Maison du sorcier.
     * @param attaque Niveau d'attaque du sorcier.
     * @param sante Santé initiale du sorcier.
     * @param modifiable Indique si le sorcier peut être modifié.
     */
    public Sorcier(int id, String nom, String prenom, Maison maison, int attaque, int sante, boolean modifiable) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.maison = maison;
        this.attaque = attaque;
        this.sante = sante;
        this.modifiable = modifiable;
    }

    /**
     * Constructeur de la classe Sorcier sans l'ID.
     * @param nom Nom du sorcier.
     * @param prenom Prénom du sorcier.
     * @param maison Maison du sorcier.
     * @param attaque Niveau d'attaque du sorcier.
     * @param sante Santé initiale du sorcier.
     * @param modifiable Indique si le sorcier peut être modifié.
     */
    public Sorcier(String nom, String prenom, Maison maison, int attaque, int sante, boolean modifiable) {
        this.nom = nom;
        this.prenom = prenom;
        this.maison = maison;
        this.attaque = attaque;
        this.sante = sante;
        this.modifiable = modifiable;
    }

    /**
     * Obtient l'identifiant unique du sorcier.
     * @return L'identifiant du sorcier.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du sorcier.
     * @param id Le nouvel identifiant.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom du sorcier.
     * @return Le nom du sorcier.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du sorcier.
     * @param nom Le nouveau nom du sorcier.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom du sorcier.
     * @return Le prénom du sorcier.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du sorcier.
     * @param prenom Le nouveau prénom du sorcier.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient le niveau d'attaque du sorcier.
     * @return Le niveau d'attaque.
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Définit le niveau d'attaque du sorcier.
     * @param attaque Le nouveau niveau d'attaque.
     */
    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    /**
     * Obtient la santé actuelle du sorcier.
     * @return La santé actuelle.
     */
    public int getSante() {
        return sante;
    }

    /**
     * Définit la santé du sorcier.
     * @param sante La nouvelle santé.
     */
    public void setSante(int sante) {
        this.sante = sante;
    }

    /**
     * Obtient la santé maximale du sorcier.
     * @return La santé maximale.
     */
    public int getMaxSante() {
        return maxSante;
    }

    /**
     * Définit la santé maximale du sorcier.
     * @param maxSante La nouvelle santé maximale.
     */
    public void setMaxSante(int maxSante) {
        this.maxSante = maxSante;
    }

    /**
     * Vérifie si le sorcier est modifiable.
     * @return Vrai si le sorcier est modifiable, faux sinon.
     */
    public boolean isModifiable() {
        return modifiable;
    }

    /**
     * Définit si le sorcier peut être modifié.
     * @param modifiable Le nouvel état modifiable.
     */
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    /**
     * Obtient la maison associée au sorcier.
     * @return La maison associée.
     */
    public Maison getMaison() {
        return maison;
    }

    /**
     * Définit la maison associée au sorcier.
     * @param maison La nouvelle maison associée.
     */
    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Sorcier.
     * @return Chaîne de caractères représentant le sorcier.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Sorcier [");
        builder.append("id=").append(id);
        builder.append(", nom=").append(nom);
        builder.append(", prenom=").append(prenom);
        builder.append(", maison=").append(maison);
        builder.append(", attaque=").append(attaque);
        builder.append(", sante=").append(sante);
        builder.append(", modifiable=").append(modifiable);
        builder.append("]");
        return builder.toString();
    }
}
