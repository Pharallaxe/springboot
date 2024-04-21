package com.example.sorcier.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sorcier.bo.Maison;
import com.example.sorcier.dal.MaisonRepositorySqlite;

/**
 * Service pour la gestion des maisons utilisant SQLite.
 * Ce service implémente l'interface MaisonService et utilise un profil spécifique 'sqlite'
 * pour distinguer cette implémentation spécifique de la base de données SQLite.
 */
@Service
public class MaisonServiceSqlite implements MaisonService {
    private MaisonRepositorySqlite maisonRepository;
    
    /**
     * Constructeur pour l'injection de dépendance du repository SQLite pour les maisons.
     * @param maisonRepository Le repository utilisé pour accéder aux données des maisons.
     */
    public MaisonServiceSqlite(MaisonRepositorySqlite maisonRepository) {
        this.maisonRepository = maisonRepository;
    }

    /**
     * Récupère la liste de toutes les maisons stockées dans la base de données SQLite.
     * @return La liste de toutes les maisons.
     */
    @Override
    public List<Maison> getMaisons() {
        return maisonRepository.findAll();
    }

    /**
     * Ajoute une nouvelle maison à la base de données SQLite.
     * @param maison L'objet Maison à ajouter.
     */
    @Override
    public void ajouterMaison(Maison maison) {
        maisonRepository.save(maison);
    }

    /**
     * Récupère une maison par son identifiant dans la base de données SQLite.
     * @param id L'identifiant de la maison à récupérer.
     * @return L'objet Maison correspondant, ou null si non trouvé.
     */
    @Override
    public Maison recupererMaison(int id) {
        return maisonRepository.findById(id).orElse(null);
    }

    /**
     * Supprime une maison de la base de données SQLite selon son identifiant.
     * @param id L'identifiant de la maison à supprimer.
     */
    @Override
    public void supprimerMaison(int id) {
        maisonRepository.deleteById(id);
    }

    /**
     * Met à jour les informations d'une maison existante dans la base de données SQLite.
     * @param maison L'objet Maison avec les informations mises à jour.
     */
    @Override
    public void modifierMaison(Maison maison) {
        maisonRepository.save(maison);
    }
    
    @Override
	public List<Maison> rechercherMaisons(String motARechercher) {
		if (motARechercher.equals("")) {
			return getMaisons();
		}
		else {
			return getMaisons()
					.stream()
					.filter(m ->
						m.getNom().toLowerCase().contains(motARechercher.toLowerCase()))
					.toList();
		}
	}
}
