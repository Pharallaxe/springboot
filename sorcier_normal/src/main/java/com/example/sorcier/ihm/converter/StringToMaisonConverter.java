package com.example.sorcier.ihm.converter;

// Import pour utiliser le mécanisme de conversion de Spring.
import org.springframework.core.convert.converter.Converter;

// Import pour marquer la classe comme un composant Spring.
import org.springframework.stereotype.Component;

import com.example.sorcier.bll.MaisonService;
import com.example.sorcier.bo.Maison;

/**
 * Convertisseur pour convertir une chaîne représentant l'ID d'une maison en objet Maison.
 */
@Component
public class StringToMaisonConverter implements Converter<String, Maison> {
    private MaisonService maisonService;

    /**
     * Constructeur pour injecter le service de gestion des maisons.
     * 
     * @param maisonService - Le service de gestion des maisons.
     */
    public StringToMaisonConverter(MaisonService maisonService) {
        this.maisonService = maisonService;
    }

    /**
     * Convertit l'ID de type String en un objet Maison.
     * 
     * @param id - L'id de la maison sous forme de chaîne de caractères.
     * @return L'objet Maison correspondant à l'ID ou null si non trouvé.
     */
    @Override
    public Maison convert(String id) {
        // Convertit la chaîne en un entier représentant l'ID de la maison.
        int id_maison = Integer.parseInt(id);
        
        // Récupère l'objet Maison à partir de son ID via le service.
        Maison maisonRetour = maisonService.recupererMaison(id_maison);
        
        // Retourne l'objet Maison récupéré.
        return maisonRetour;
    }
}
