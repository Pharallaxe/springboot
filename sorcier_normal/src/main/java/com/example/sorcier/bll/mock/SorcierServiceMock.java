package com.example.sorcier.bll.mock;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.sorcier.bll.SorcierService;
import com.example.sorcier.bll.metier.SorcierMetier;
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.dal.mock.MaisonRepositoryMock;
import com.example.sorcier.dal.mock.SorcierRepositoryMock;

@Service
@Profile("dev")
public class SorcierServiceMock implements SorcierService {
	
	private SorcierRepositoryMock sorcierRepositoryMock;
	private MaisonRepositoryMock maisonRepositoryMock;

	// CONSTRUCTEUR

	/**
	 * Constructeur de SorcierServiceMock.
	 * 
	 * @param sorcierRepositoryMock - Le mock du repository de Sorcier.
	 * @param maisonRepositoryMock  - Le mock du repository de Maison.
	 */
	public SorcierServiceMock(SorcierRepositoryMock sorcierRepositoryMock, MaisonRepositoryMock maisonRepositoryMock) {
		this.sorcierRepositoryMock = sorcierRepositoryMock;
		this.maisonRepositoryMock = maisonRepositoryMock;
	}

	// FONCTIONS DAL

	/**
	 * Ajoute un sorcier.
	 * 
	 * @param sorcier - Le sorcier à ajouter.
	 */
	public void ajouterSorcier(Sorcier sorcier) {
		// Recherche du sorcier par nom dans le mock du repository de Sorcier.
		Sorcier sorcierTrouve = sorcierRepositoryMock.findByName(sorcier);
		
		// Vérifie si le sorcier existe déjà.
		if (sorcierTrouve != null) {
	    	System.out.println("SSM : Sorcier déjà existant (ajouterSorcier).");
	    	return;
		}
		
		// Sauvegarde du sorcier dans le mock du repository de Sorcier.
		sorcierRepositoryMock.save(sorcier);
	}

	/**
	 * Ajoute un sorcier à une maison.
	 * 
	 * @param sorcier - Le sorcier à ajouter.
	 */
	@Override
	public void ajouterSorcierMaison(Sorcier sorcier) {
		// Recherche le sorcier par nom dans le mock du repository de Sorcier.
		Sorcier sorcierTrouve = sorcierRepositoryMock.findByName(sorcier);

		// Vérifie si le sorcier existe déjà.
		if (sorcierTrouve != null) {
			System.out.println("SSM : Sorcier déjà existant (ajouterSorcierMaison).");
			return;
		}
		
		// Obtient la maison associée au sorcier.
		Maison maison = sorcier.getMaison();
		
		// Recherche la maison par nom dans le mock du repository de Maison.
		Maison maisonTrouvee = maisonRepositoryMock.findByName(maison);

		// Valide si la maison existe déjà.
		if (maisonTrouvee != null) {
			System.out.println("SSM : Maison déjà existante (ajouterSorcierMaison).");
			return;
		}

		// Sauvegarde la maison dans le mock du repository de Maison.
		maisonRepositoryMock.save(maison);
		
		// Associe la maison au sorcier.
		sorcier.setMaison(maison);
		
		// Sauvegarde le sorcier dans le mock du repository sorcier.
		sorcierRepositoryMock.save(sorcier);
	}

	/**
	 * Modifie un sorcier.
	 * 
	 * @param sorcier Le sorcier à modifier.
	 */
	public void modifierSorcier(Sorcier sorcier) {
		// Met à jour le sorcier dans le mock du repository du sorcier.
		sorcierRepositoryMock.update(sorcier);
	}

	/**
	 * Récupère un sorcier par son identifiant.
	 * 
	 * @param id - L'id du sorcier à récupérer.
	 * @return Le sorcier correspondant à l'identifiant spécifié.
	 */
	public Sorcier recupererSorcier(int id) {
		// Récupére le sorcier par son identifiant depuis le mock du repository sorcier.
		return sorcierRepositoryMock.findById(id);
	}

	/**
	 * Récupère tous les sorciers.
	 * 
	 * @return La liste de tous les sorciers
	 */
	public List<Sorcier> recupererSorciers() {
		// Récupére tous les sorciers depuis le mock du repository sorcier
		return sorcierRepositoryMock.findAll();
	}

	/**
	 * Supprime un sorcier par son identifiant.
	 * 
	 * @param id - L'id du sorcier à supprimer.
	 */
	public void supprimerSorcier(int id) {
		// Supprime le sorcier avec un id dans le mock du repository sorcier
		sorcierRepositoryMock.removeById(id);
	}

	// FONCTIONS METIER

	/**
	 * Recherche les sorciers par un mot spécifique.
	 * 
	 * @param motARechercher - Le mot à rechercher dans les noms des sorciers.
	 * @return La liste des sorciers correspondant au critère de recherche.
	 */
	public List<Sorcier> rechercherSorciers(String motARechercher) {
		// Appelle la méthode de la classe métier pour rechercher les sorciers
		return SorcierMetier.rechercherSorciers(motARechercher, recupererSorciers());
	}

	/**
	 * Compte le nombre de sorciers.
	 * 
	 * @return Le nombre de sorciers.
	 */
	public String compterSorciers() {
		// Appelle la méthode de la couche métier pour compter les sorciers.
		return SorcierMetier.compterSorciers(recupererSorciers());
	}
}
