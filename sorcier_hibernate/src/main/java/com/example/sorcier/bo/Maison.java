package com.example.sorcier.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "maison")
public class Maison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Pattern(regexp = "^[a-zA-Z]{4,20}$", message = "Le nom doit contenir entre 4 et 20 lettres alphabétiques.")
    @Column(length = 20, nullable = false)
    protected String nom;

    @Min(value = 0, message = "Le bonus d'attaque doit être au moins de 0")
    @Max(value = 8, message = "Le bonus d'attaque ne peut pas dépasser 8")
    @Column(nullable = false)
    protected int bonus_attaque;

    @Min(value = 0, message = "Le bonus de santé doit être au moins de 0")
    @Max(value = 30, message = "Le bonus de santé ne peut pas dépasser 30")
    @Column(nullable = false)
    protected int bonus_sante;

    @Column(nullable = false)
    protected boolean modifiable = true;

    public Maison() {
	}

	public Maison(int id,
			String nom,
			int bonus_attaque,
			int bonus_sante,
			boolean modifiable) {
		this.id = id;
		this.nom = nom;
		this.bonus_attaque = bonus_attaque;
		this.bonus_sante = bonus_sante;
		this.modifiable = modifiable;
	}

	public Maison(
			String nom,
			int bonus_attaque,
			int bonus_sante,
			boolean modifiable) {
		this.nom = nom;
		this.bonus_attaque = bonus_attaque;
		this.bonus_sante = bonus_sante;
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

	public int getBonus_attaque() {
		return bonus_attaque;
	}

	public void setBonus_attaque(int bonus_attaque) {
		this.bonus_attaque = bonus_attaque;
	}

	public int getBonus_sante() {
		return bonus_sante;
	}

	public void setBonus_sante(int bonus_sante) {
		this.bonus_sante = bonus_sante;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

}
