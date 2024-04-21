package com.example.sorcier.bll.metier;

import com.example.sorcier.bo.Sorcier;
import com.example.sorcier.bo.Utilisateur;

/**
 * Classe métier fournissant des opérations liées à la gestion des utilisateurs.
 */
public class UtilisateurMetier {

    /**
     * Retourne une description d'un sorcier associé à un utilisateur.
     * 
     * @param utilisateur - L'utilisateur dont le sorcier est à afficher.
     * @return Description du sorcier ou message indiquant l'absence de sorcier.
     */
    public static String afficherSorcierUtilisateur(Utilisateur utilisateur) {
        // Récupère le sorcier associé à l'utilisateur.
        Sorcier sorcier = utilisateur.getSorcier();
        
        // Utilise SorcierMetier pour obtenir la description du sorcier.
        return SorcierMetier.afficherSorcier(sorcier);
    }
}
