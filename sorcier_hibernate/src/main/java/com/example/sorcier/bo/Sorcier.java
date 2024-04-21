package com.example.sorcier.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "sorcier")
public class Sorcier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 25, nullable = false)
    @Pattern(regexp = "^[a-zA-Z]{3,25}$")
    private String nom;

    @Column(length = 25, nullable = false)
    @Pattern(regexp = "^[a-zA-Z]{3,25}$")
    private String prenom;

    @Column(nullable = false)
    @Min(value = 2)
    @Max(value = 18)
    private int attaque;

    @Column(nullable = false)
    @Min(value = 40)
    @Max(value = 300)
    private int sante;

    @Column(nullable = false)
    private boolean modifiable = true;

    @ManyToOne
    @JoinColumn(name = "id_maison")
    private Maison maison;

    public Sorcier() {
    }
    
    public Sorcier(int id, String nom, String prenom, Maison maison, int attaque, int sante, boolean modifiable) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.attaque = attaque;
		this.sante = sante;
		this.modifiable = modifiable;
		this.maison = maison;
	}
    
    public Sorcier(String nom, String prenom, Maison maison, int attaque, int sante, boolean modifiable) {
        this.nom = nom;
        this.prenom = prenom;
        this.maison = maison;
        this.attaque = attaque;
        this.sante = sante;
        this.modifiable = modifiable;
    }

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getSante() {
        return sante;
    }

    public void setSante(int sante) {
        this.sante = sante;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }
}
