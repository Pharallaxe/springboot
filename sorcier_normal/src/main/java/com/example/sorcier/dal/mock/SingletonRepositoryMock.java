package com.example.sorcier.dal.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Role;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.bo.Utilisateur;

/**
 * Un singleton mock repository pour simuler le stockage et la récupération
 * d'informations sur les maisons et les sorciers. Utilisé principalement
 * pour le développement et les tests.
 */
public class SingletonRepositoryMock {
    private List<Maison> maisons = new ArrayList<>();
    private List<Sorcier> sorciers = new ArrayList<>();
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private static SingletonRepositoryMock instance = null;


    public static SingletonRepositoryMock getInstance() {
        if (instance == null) {
            instance = new SingletonRepositoryMock();
        }
        return instance;
    }
    
    private SingletonRepositoryMock() {
        // Initialise des maisons
        Maison gryffondor = new Maison(1, "Gryffondor", 5, 10, false);
        Maison serdaigle = new Maison(2, "Serdaigle", 1, 30, false);
        Maison poufsouffle = new Maison(3, "Poufsouffle", 4, 20, false);
        Maison serpentard = new Maison(4, "Serpentard", 8, 0, false);
        Maison hippogriffe = new Maison(5, "Hippogriffe", 4, 25, true);
        Maison moldu = new Maison(6, "Moldu", 3, 20, true);
        
        maisons.add(gryffondor);
        maisons.add(serdaigle);
        maisons.add(poufsouffle);
        maisons.add(serpentard);
        maisons.add(hippogriffe);
        maisons.add(moldu);
        
     	// Initialise des sorciers
        Sorcier harry = new Sorcier(1, "Potter", "Harry", gryffondor, 10, 100, false);
        sorciers.add(harry);
        sorciers.add(new Sorcier(3, "Lovegood", "Luna", serdaigle, 3, 200, false));
        sorciers.add(new Sorcier(4, "Patil", "Padma", serdaigle, 4, 170, false));
        sorciers.add(new Sorcier(5, "Diggory", "Cedric", poufsouffle, 7, 150, false));
        sorciers.add(new Sorcier(6, "Chourave", "Pomona", poufsouffle, 8, 140, false));
        sorciers.add(new Sorcier(7, "Rogue", "Severus", serpentard, 15, 80, false));
        sorciers.add(new Sorcier(8, "Malfoy", "Draco", serpentard, 11, 60, false));
        sorciers.add(new Sorcier(9, "McGregor", "Edward", hippogriffe, 8, 150, true));
        sorciers.add(new Sorcier(10,"Sword", "William", moldu, 5, 250, true));
        
        // Initialise des utilisateurs
        Utilisateur utilisateur1 = new Utilisateur(1, "aze", "aze@aze.fr", "$2a$10$Hq8jz5Enlil/4VSKAzMD/eQJkVBXogL9HbwOHSpdo2WR3A6lroHXG", 1, Timestamp.valueOf("2020-02-03 14:00:00"), harry);
	    Utilisateur utilisateur2 = new Utilisateur(2, "john", "john@doe.fr", "$2a$10$/F0URepnPYgACx0siUaTDOndAA5BSvlmXRku3wt9LZNIg3fL3F1Ju", 1, Timestamp.valueOf("2020-02-03 14:00:00"));
	    Utilisateur utilisateur3 = new Utilisateur(3, "guillaume", "guillaume@doe.fr", "$2a$10$RBdEYyVSjVHtYGejvjrsa.uqc29qzWaBbF14zeHTMqLkZNKXSHHX.", 0, Timestamp.valueOf("2020-02-03 14:00:00"), harry);
        utilisateurs.add(utilisateur1);
        utilisateurs.add(utilisateur2);
        utilisateurs.add(utilisateur3);
        
        // Initialise des utilisateurs
        roles.add(new Role(1, utilisateur1.getId(), "ADMIN"));
        roles.add(new Role(2, utilisateur1.getId(), "INSCRIT"));
        roles.add(new Role(3, utilisateur2.getId(), "INSCRIT"));
        roles.add(new Role(4, utilisateur3.getId(), "INSCRIT"));
        
    }

    public List<Maison> getMaisons() {
        return maisons;
    }

    public void setMaisons(List<Maison> maisons) {
        this.maisons = maisons;
    }

    public List<Sorcier> getSorciers() {
        return sorciers;
    }

    public void setSorciers(List<Sorcier> sorciers) {
        this.sorciers = sorciers;
    }

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
