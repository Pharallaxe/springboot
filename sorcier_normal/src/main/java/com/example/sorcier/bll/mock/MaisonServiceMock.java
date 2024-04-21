package com.example.sorcier.bll.mock;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.sorcier.bll.MaisonService;
import com.example.sorcier.bo.Maison;
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.dal.mock.MaisonRepositoryMock;
import com.example.sorcier.dal.mock.SorcierRepositoryMock;

@Service
@Profile("dev")
public class MaisonServiceMock implements MaisonService {

	private SorcierRepositoryMock sorcierRepositoryMock;
	private MaisonRepositoryMock maisonRepositoryMock;

	/**
	 * Constructeur de MaisonServiceMock.
	 * 
	 * @param sorcierRepositoryMock - Le mock du repository de Sorcier.
	 * @param maisonRepositoryMock  - Le mock du repository de Maison.
	 */
	public MaisonServiceMock(SorcierRepositoryMock sorcierRepositoryMock, MaisonRepositoryMock maisonRepositoryMock) {
		this.sorcierRepositoryMock = sorcierRepositoryMock;
		this.maisonRepositoryMock = maisonRepositoryMock;
	}

	// FONCTIONS DAL

	/**
	 * Ajoute une maison.
	 * 
	 * @param maison - La maison à ajouter.
	 */
	@Override
	public void ajouterMaison(Maison maison) {
		// Recherche de la maison par nom dans le mock du repository de Maison.
		Maison maisonTrouvee = maisonRepositoryMock.findByName(maison);

		// Vérifie si la maison existe déjà.
		if (maisonTrouvee != null) {
			System.out.println("MSM : Maison déjà existante (ajouterMaison).");
			return;
		}
		// Sauvegarde de la maison dans le mock du repository de Maison.
		maisonRepositoryMock.save(maison);
	}

	/**
	 * Modifie une maison.
	 * 
	 * @param maison La maison à modifier.
	 */
	@Override
	public void modifierMaison(Maison maison) {
		// Met à jour la maison dans le mock du repository de Maison.
		maisonRepositoryMock.update(maison);
	}

	/**
	 * Récupère une maison par son identifiant.
	 * 
	 * @param id L'identifiant de la maison à récupérer.
	 * @return La maison correspondant à l'identifiant spécifié.
	 */
	@Override
	public Maison recupererMaison(int id) {
		// Récupére la maison par son identifiant depuis le mock du repository de Maison.
		return maisonRepositoryMock.findById(id);
	}

	/**
	 * Récupère toutes les maisons.
	 * 
	 * @return La liste de toutes les maisons.
	 */
	@Override
	public List<Maison> recupererMaisons() {
		// Récupére toutes les maisons depuis le mock du repository de Maison.
		return maisonRepositoryMock.findAll();
	}

	/**
	 * Supprime une maison par son identifiant.
	 * 
	 * @param id_maison - L'id de la maison à supprimer.
	 */
	@Override
	public void supprimerMaison(int id_maison) {
		// Recherche les sorciers associés à la maison à supprimer.
		List<Sorcier> sorciersTrouve = sorcierRepositoryMock.findByMaison(id_maison);

		// Vérifie si des sorciers sont associés à la maison.
		if (sorciersTrouve.size() > 0) {
			System.out.println("MSM : Maison utilisée (supprimerMaison).");
			return;
		}
		// Supprime la maison dans le mock du repository de Maison.
		maisonRepositoryMock.removeById(id_maison);
	}

	// FONCTIONS METIER

	/**
	 * Recherche les maisons par un mot spécifique.
	 * 
	 * @param motARechercher - Le mot à rechercher dans les noms des maisons.
	 * @return La liste des maisons correspondant au critère de recherche.
	 */
	@Override
	public List<Maison> rechercherMaisons(String motARechercher) {
		if (motARechercher.equals("")) {
			// Récupére toutes les maisons si le mot de recherche est vide.
			return recupererMaisons();
			
		} else {
			// Filtre les maisons dont le nom contient le mot de recherche (insensible à la casse).
			return recupererMaisons().stream()
					.filter(m -> m.getNom().toLowerCase().contains(motARechercher.toLowerCase())).toList();
		}
	}
}
