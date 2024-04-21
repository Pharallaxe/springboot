package com.example.sorcier.bo;

import java.util.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class Utilisateur {
    protected int id;
	
    // Définition des contraintes de validation pour le nom
    @Pattern(regexp = "^[a-zA-Z]{2,35}$")
    protected String nom;
	
    // Définition de la contrainte de validation pour l'email
    @Email()
    protected String email;	
	  
    protected String mdpHash;
	
    // Définition des contraintes de validation pour le niveau
    @Min(value = 0)
    @Max(value = 1000000)
    protected int niveau = 0;
	
    protected Date dateInscription = new Date();
    
    // Validation du mot de passe à l'aide d'une annotation @Valid
    @Valid()
    protected MotDePasse motDePasse = null;
	
    protected Sorcier sorcier = null;
	
    /**
     * Constructeur par défaut.
     */
    public Utilisateur() {
    }

    /**
     * Constructeur avec tous les champs.
     * 
     * @param id L'identifiant de l'utilisateur
     * @param nom Le nom de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param mdpHash Le hachage du mot de passe de l'utilisateur
     * @param niveau Le niveau de l'utilisateur
     * @param dateInscription La date d'inscription de l'utilisateur
     * @param sorcier Le sorcier associé à l'utilisateur
     */
    public Utilisateur(int id, String nom, String email, String mdpHash,
            int niveau, Date dateInscription, Sorcier sorcier) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mdpHash = mdpHash;
        this.niveau = niveau;
        this.dateInscription = dateInscription;
        this.sorcier = sorcier;
    }

    /**
     * Constructeur sans l'identifiant.
     * 
     * @param nom Le nom de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param mdpHash Le hachage du mot de passe de l'utilisateur
     * @param niveau Le niveau de l'utilisateur
     * @param dateInscription La date d'inscription de l'utilisateur
     * @param sorcier Le sorcier associé à l'utilisateur
     */
    public Utilisateur(String nom, String email, String mdpHash,
            int niveau, Date dateInscription, Sorcier sorcier) {
        this.nom = nom;
        this.email = email;
        this.mdpHash = mdpHash;
        this.niveau = niveau;
        this.dateInscription = dateInscription;
        this.sorcier = sorcier;
    }
    
    /**
     * Constructeur avec tous les champs sauf l'identifiant.
     * 
     * @param id L'identifiant de l'utilisateur
     * @param nom Le nom de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param mdpHash Le hachage du mot de passe de l'utilisateur
     * @param niveau Le niveau de l'utilisateur
     * @param dateInscription La date d'inscription de l'utilisateur
     */
    public Utilisateur(int id, String nom, String email, String mdpHash,
            int niveau, Date dateInscription) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mdpHash = mdpHash;
        this.niveau = niveau;
        this.dateInscription = dateInscription;
    }
    
    /**
     * Constructeur sans l'identifiant et le sorcier.
     * 
     * @param nom Le nom de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param mdpHash Le hachage du mot de passe de l'utilisateur
     * @param niveau Le niveau de l'utilisateur
     * @param dateInscription La date d'inscription de l'utilisateur
     */
    public Utilisateur(String nom, String email, String mdpHash,
            int niveau, Date dateInscription) {
        this.nom = nom;
        this.email = email;
        this.mdpHash = mdpHash;
        this.niveau = niveau;
        this.dateInscription = dateInscription;
    }

    /**
     * Obtient l'identifiant de l'utilisateur.
     * 
     * @return L'identifiant de l'utilisateur
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     * 
     * @param id Le nouvel identifiant de l'utilisateur
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom de l'utilisateur.
     * 
     * @return Le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur.
     * 
     * @param nom Le nouveau nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient l'email de l'utilisateur.
     * 
     * @return L'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'email de l'utilisateur.
     * 
     * @param email Le nouvel email de l'utilisateur
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtient le hachage du mot de passe de l'utilisateur.
     * 
     * @return Le hachage du mot de passe de l'utilisateur
     */
    public String getMdpHash() {
        return mdpHash;
    }

    /**
     * Définit le hachage du mot de passe de l'utilisateur.
     * 
     * @param mdpHash Le nouveau hachage du mot de passe de l'utilisateur
     */
    public void setMdpHash(String mdpHash) {
        this.mdpHash = mdpHash;
    }

    /**
     * Obtient le niveau de l'utilisateur.
     * 
     * @return Le niveau de l'utilisateur
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * Définit le niveau de l'utilisateur.
     * 
     * @param niveau Le nouveau niveau de l'utilisateur
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * Obtient le sorcier associé à l'utilisateur.
     * 
     * @return Le sorcier associé à l'utilisateur
     */
    public Sorcier getSorcier() {
        return sorcier;
    }

    /**
     * Définit le sorcier associé à l'utilisateur.
     * 
     * @param sorcier Le nouveau sorcier associé à l'utilisateur
     */
    public void setSorcier(Sorcier sorcier) {
        this.sorcier = sorcier;
    }

    /**
     * Obtient la date d'inscription de l'utilisateur.
     * 
     * @return La date d'inscription de l'utilisateur
     */
    public Date getDateInscription() {
        return dateInscription;
    }

    /**
     * Définit la date d'inscription de l'utilisateur.
     * 
     * @param dateInscription La nouvelle date d'inscription de l'utilisateur
     */
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     * 
     * @return Le mot de passe de l'utilisateur
     */
    public MotDePasse getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * 
     * @param motDePasse Le nouveau mot de passe de l'utilisateur
     */
    public void setMotDePasse(MotDePasse motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Obtient une représentation sous forme de chaîne de caractères de l'utilisateur.
     * 
     * @return Une représentation sous forme de chaîne de caractères de l'utilisateur
     */
    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", email=" + email + ", mdpHash=" + mdpHash
                + ", niveau=" + niveau + ", dateInscription=" + dateInscription + ", sorcier=" + sorcier + "]";
    }
}
