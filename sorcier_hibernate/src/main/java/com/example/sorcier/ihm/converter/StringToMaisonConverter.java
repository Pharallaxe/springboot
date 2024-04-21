package com.example.sorcier.ihm.converter;

// Importations nécessaires pour la conversion et le composant Spring
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Importations des services et objets métiers (business objects)
import com.example.sorcier.bll.MaisonService;
import com.example.sorcier.bo.Maison;

/**
 * Composant Spring qui implémente l'interface Converter pour convertir
 * une chaîne de caractères représentant un ID de maison en un objet Maison.
 * Ce convertisseur est automatiquement détecté et utilisé par Spring
 * lorsqu'un ID de maison est présent dans une requête et doit être converti
 * en objet Maison pour être utilisé dans le contrôleur ou le service.
 */
@Component
public class StringToMaisonConverter implements Converter<String, Maison> {
    private MaisonService maisonService;

    /**
     * Constructeur pour l'injection de dépendance du service de gestion des maisons.
     * @param maisonService Le service pour accéder aux opérations de gestion des maisons.
     */
    public StringToMaisonConverter(MaisonService maisonService) {
        this.maisonService = maisonService;
    }

    /**
     * Convertit la chaîne d'entrée, qui est l'ID de la maison, en un objet Maison.
     * @param id La chaîne représentant l'ID de la maison à convertir.
     * @return L'objet Maison correspondant à l'ID fourni.
     */
    @Override
    public Maison convert(String id) {
        // Conversion de l'ID de type String en type int
        int id_maison = Integer.parseInt(id);
        
        // Récupération de l'objet Maison correspondant à l'ID
        Maison maisonRetour = maisonService.recupererMaison(id_maison);
        
        return maisonRetour;
    }
}
