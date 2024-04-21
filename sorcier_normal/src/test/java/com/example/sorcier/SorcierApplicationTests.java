package com.example.sorcier;

import java.sql.Timestamp;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.bo.Utilisateur;
import com.example.sorcier.dal.sqlserver.MaisonRepositorySqlserver;
import com.example.sorcier.dal.sqlserver.SorcierRepositorySqlserver;
import com.example.sorcier.dal.sqlserver.UtilisateurRepositorySqlserver;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SorcierNormalApplicationTests {

	@Autowired
	private SorcierRepositorySqlserver sorcierRepositorySqlserver;
	@Autowired
	private MaisonRepositorySqlserver maisonRepositorySqlserver;
	@Autowired
	private UtilisateurRepositorySqlserver utilisateurRepositorySqlserver;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@Order(1)
	void setupDatabase() {
		// Suppression des tables si elles existent
		jdbcTemplate.execute("DROP TABLE IF EXISTS Roles");
		jdbcTemplate.execute("DROP TABLE IF EXISTS Utilisateur");
		jdbcTemplate.execute("DROP TABLE IF EXISTS Sorcier");
		jdbcTemplate.execute("DROP TABLE IF EXISTS Maison");

		// Création de la table Maison
		jdbcTemplate.execute("CREATE TABLE Maison (" 
				+ "id INT PRIMARY KEY IDENTITY,"
				+ "nom VARCHAR(255) NOT NULL,"
				+ "bonusAttaque INT,"
				+ "bonusSante INT,"
				+ "modifiable INT)");

		// Création de la table Sorcier
		jdbcTemplate.execute("CREATE TABLE Sorcier ("
				+ "id INT PRIMARY KEY IDENTITY ,"
				+ "nom VARCHAR(255), "
				+ "prenom VARCHAR(255), "
				+ "id_maison INT, "
				+ "attaque INT, "
				+ "sante INT, "
				+ "modifiable INT, "
				+ "FOREIGN KEY (id_maison) REFERENCES Maison(id))");

		// Création de la table Utilisateur
		jdbcTemplate.execute("CREATE TABLE Utilisateur ("
			    + "id INT PRIMARY KEY IDENTITY, "
			    + "nom VARCHAR(255) NOT NULL, "
			    + "email VARCHAR(255) NOT NULL, "
			    + "mdpHash VARCHAR(255) NOT NULL,"
			    + "niveau INT,"
			    + "id_sorcier INT,"
			    + "dateInscription DATETIME2 NOT NULL,"
			    + "FOREIGN KEY (id_sorcier) REFERENCES Sorcier(id))");
		
		
		// Création de la table Rôles
		jdbcTemplate.execute("CREATE TABLE Roles (" +
		        "    id_utilisateur INT, " +
		        "    role VARCHAR(50) NOT NULL, " +
		        "    PRIMARY KEY (id_utilisateur, role), " +
		        "    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id)" +
		        ");");
	}

	@Test
	@Order(2)
	void testShouldInsertMaisonData() {
		
		// Maisons
        Maison gryffondor = new Maison("Gryffondor", 5, 10, false);
        Maison serdaigle = new Maison("Serdaigle", 1, 30, false);
        Maison poufsouffle = new Maison("Poufsouffle", 4, 20, false);
        Maison serpentard = new Maison("Serpentard", 8, 0, false);
        Maison hippogriffe = new Maison("Hippogriffe", 4, 25, true);
        Maison moldu = new Maison("Moldu", 3, 20, true);
        maisonRepositorySqlserver.save(gryffondor);
        maisonRepositorySqlserver.save(serdaigle);
        maisonRepositorySqlserver.save(poufsouffle);
        maisonRepositorySqlserver.save(serpentard);
        maisonRepositorySqlserver.save(hippogriffe);
        maisonRepositorySqlserver.save(moldu);
        
        // Sorciers
        Sorcier harry = new Sorcier(1, "Potter", "Harry", gryffondor, 10, 100, false);
        sorcierRepositorySqlserver.save(harry);
        sorcierRepositorySqlserver.save(new Sorcier(2, "Granger", "Hermione", gryffondor, 9, 120, false));
        sorcierRepositorySqlserver.save(new Sorcier(3, "Lovegood", "Luna", serdaigle, 3, 200, false));
        sorcierRepositorySqlserver.save(new Sorcier(4, "Patil", "Padma", serdaigle, 4, 170, false));
        sorcierRepositorySqlserver.save(new Sorcier(5, "Diggory", "Cedric", poufsouffle, 7, 150, false));
        sorcierRepositorySqlserver.save(new Sorcier(6, "Chourave", "Pomona", poufsouffle, 8, 140, false));
        sorcierRepositorySqlserver.save(new Sorcier(7, "Rogue", "Severus", serpentard, 15, 80, false));
        sorcierRepositorySqlserver.save(new Sorcier(8, "Malfoy", "Draco", serpentard, 11, 60, false));
        sorcierRepositorySqlserver.save(new Sorcier(9, "McGregor", "Edward", hippogriffe, 8, 150, true));
        sorcierRepositorySqlserver.save(new Sorcier(10,"Sword", "William", moldu, 5, 250, true));
        
        // Utilisateurs
	    Utilisateur utilisateur1 = new Utilisateur("aze", "aze@aze.fr", "$2a$10$Hq8jz5Enlil/4VSKAzMD/eQJkVBXogL9HbwOHSpdo2WR3A6lroHXG", 1, Timestamp.valueOf("2020-02-03 14:00:00"), harry);
	    Utilisateur utilisateur2 = new Utilisateur("john", "john@doe.fr", "$2a$10$/F0URepnPYgACx0siUaTDOndAA5BSvlmXRku3wt9LZNIg3fL3F1Ju", 1, Timestamp.valueOf("2020-02-03 14:00:00"));
	    utilisateurRepositorySqlserver.save(utilisateur1);
	    utilisateurRepositorySqlserver.save(utilisateur2);

	    jdbcTemplate.execute("INSERT INTO Roles (id_utilisateur, role) VALUES " +
	    	    "((SELECT id FROM Utilisateur WHERE nom = 'aze'), 'ROLE_INSCRIT'), " +
	    	    "((SELECT id FROM Utilisateur WHERE nom = 'aze'), 'ROLE_ADMIN'), " +
	    	    "((SELECT id FROM Utilisateur WHERE nom = 'john'), 'ROLE_INSCRIT');");
	}
}
