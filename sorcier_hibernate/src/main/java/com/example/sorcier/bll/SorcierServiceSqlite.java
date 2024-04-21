package com.example.sorcier.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.dal.SorcierRepositorySqlite;

/**
 * Service pour la gestion des sorciers utilisant SQLite. Ce service implémente
 * l'interface SorcierService et est activé uniquement sous le profil 'sqlite',
 * permettant ainsi une séparation claire entre les implémentations pour
 * différentes bases de données.
 */
@Service
public class SorcierServiceSqlite implements SorcierService {
	private SorcierRepositorySqlite sorcierRepository;

	/**
	 * Constructeur pour l'injection de dépendance du repository spécifique à
	 * SQLite.
	 * 
	 * @param sorcierRepository Le repository pour les opérations de
	 *                                persistance des sorciers.
	 */
	public SorcierServiceSqlite(SorcierRepositorySqlite sorcierRepository) {
		this.sorcierRepository = sorcierRepository;
	}

	/**
	 * Récupère la liste de tous les sorciers présents dans la base de données
	 * SQLite.
	 * 
	 * @return La liste des sorciers.
	 */
	public List<Sorcier> getSorciers() {
		return sorcierRepository.findAll();
	}

	/**
	 * Ajoute un nouveau sorcier à la base de données SQLite.
	 * 
	 * @param sorcier L'objet Sorcier à ajouter.
	 */
	public void ajouterSorcier(Sorcier sorcier) {
		sorcierRepository.save(sorcier);
	}

	/**
	 * Récupère un sorcier par son identifiant dans la base de données SQLite.
	 * 
	 * @param id L'identifiant du sorcier à récupérer.
	 * @return L'objet Sorcier correspondant, ou null si non trouvé.
	 */
	public Sorcier recupererSorcier(int id) {
		return sorcierRepository.findById(id).orElse(null);
	}

	/**
	 * Supprime un sorcier de la base de données SQLite en utilisant son
	 * identifiant.
	 * 
	 * @param id L'identifiant du sorcier à supprimer.
	 */
	public void supprimerSorcier(int id) {
		sorcierRepository.deleteById(id);
	}

	/**
	 * Met à jour les informations d'un sorcier existant dans la base de données
	 * SQLite.
	 * 
	 * @param sorcier L'objet Sorcier avec les informations à jour.
	 */
	public void modifierSorcier(Sorcier sorcier) {
		sorcierRepository.save(sorcier);
	}
	
	public List<Sorcier> rechercherSorciers(String motARechercher) {
		if (motARechercher.equals("")) {
			return getSorciers();
		}
		else {
			return getSorciers()
					.stream()
					.filter(s ->
						s.getNom().toLowerCase().contains(motARechercher.toLowerCase()) ||
						s.getPrenom().toLowerCase().contains(motARechercher.toLowerCase()) ||
						s.getMaison().getNom().toLowerCase().contains(motARechercher.toLowerCase()))
					.toList();
		}
	}

	/**
	 * Compte et retourne un message indiquant le nombre de sorciers présents dans
	 * la base de données.
	 * 
	 * @return Une chaîne de caractères indiquant le nombre de sorciers dans la
	 *         base.
	 */
	public String compterSorciers() {
		StringBuilder sb = new StringBuilder();
		sb.append("Il y a dans ma base de données sqlite ").append(getSorciers().size()).append(" sorciers.");
		return sb.toString();
	}
}
