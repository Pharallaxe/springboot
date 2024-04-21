package com.example.sorcier.bll.metier;

// Importation de la classe List de Java pour gérer les collections.
import java.util.List;

// Importation des classes métier.
import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.bo.Utilisateur;

/**
 * Classe métier fournissant des opérations liées à la gestion des sorciers.
 */
public class SorcierMetier {

    /**
     * Recherche des sorciers correspondant à un critère de recherche dans leur nom, prénom ou maison.
     * 
     * @param motARechercher - Le mot clé de recherche.
     * @param tousLesSorciers - La liste de tous les sorciers.
     * @return Liste des sorciers filtrée selon le mot clé.
     */
    public static List<Sorcier> rechercherSorciers(String motARechercher, List<Sorcier> tousLesSorciers) {
        // Retourne tous les sorciers si aucun mot clé n'est fourni.
        if (motARechercher.equals("")) {
            return tousLesSorciers;
        } else {
        	
            // Filtre les sorciers dont le nom, prénom ou nom de maison contient le mot clé.
            return tousLesSorciers
                    .stream()
                    .filter(s ->
                        s.getNom().toLowerCase().contains(motARechercher.toLowerCase()) ||
                        s.getPrenom().toLowerCase().contains(motARechercher.toLowerCase()) ||
                        s.getMaison().getNom().toLowerCase().contains(motARechercher.toLowerCase()))
                    .toList();
        }
    }
    
    
    /**
     * Crée une chaine de caractères avec le nombre de sorciers.
     * 
     * @param tousLesSorciers - La liste de tous les sorciers.
     * @return Une chaine de caractères affichant le nombre de tous les sorciers.
     */
    public static String compterSorciers(List<Sorcier> tousLesSorciers) {
        StringBuilder sb = new StringBuilder();
        sb.append("Il y a dans ma base de donnees ")
          .append(tousLesSorciers.size())
          .append(" sorciers.");
        return sb.toString();
    }
    
    
    /**
     * Retourne une description d'un utilisateur en termes de sorcier associé.
     * 
     * @param utilisateur - L'utilisateur dont on veut décrire le sorcier.
     * @return Description du sorcier ou message indiquant l'absence de sorcier.
     */
    public static String afficherSorcier(Utilisateur utilisateur) {
        // Retourne les détails du sorcier si l'utilisateur n'est pas null.
        if (utilisateur != null) {
            return afficherSorcier(utilisateur.getSorcier());
        }
        return "Utilisateur vide";
    }
    
    /**
     * Retourne une chaîne de caractères décrivant un sorcier.
     * 
     * @param sorcier - Le sorcier à décrire.
     * @return Description du sorcier ou message indiquant l'absence de sorcier.
     */
    public static String afficherSorcier(Sorcier sorcier) {
        // Construit et retourne une chaîne descriptive si le sorcier n'est pas null.
        if (sorcier != null) {
            return sorcier.getPrenom() + " " + sorcier.getNom() + " (" + sorcier.getMaison().getNom() + ")";
        }
        return "Aucun sorcier défini";
    }
}
