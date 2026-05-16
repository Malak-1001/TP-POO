import java.io.*;
import java.util.*;

public class Chargeur_zones {
    // cette class sert a depuis un fichier cree des zone
    private Ferme ferme;
    // cinstructeur pour le chageur
    public Chargeur_zones(Ferme ferme) {
        this.ferme = ferme;
    }

    /**
     * Lit le fichier et cree les zones dans la ferme
     * Retourne le nombre de zones creees avec succes
     */
    public int charger_depuis_fichier(String fichier) {
        int zones_creees = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int numero_ligne = 0;

            while ((ligne = reader.readLine()) != null) {
                numero_ligne++;
                ligne = ligne.trim();

                // Ignorer lignes vides et commentaires
                if (ligne.isEmpty() || ligne.startsWith("#")) continue;

                try {
                    boolean succes = traiter_ligne(ligne);
                    if (succes) zones_creees++;
                } catch (Exception e) {
                    System.out.println("Erreur ligne " + numero_ligne + " : " + ligne);
                    System.out.println("  -> " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + fichier);
        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        System.out.println(zones_creees + " zone(s) chargee(s) depuis " + fichier);
        return zones_creees;
    }

    /**
     * Traite une ligne et cree la zone correspondante
     * La zone s'ajoute elle-meme a la ferme via son constructeur
     */
    private boolean traiter_ligne(String ligne) {
        String[] parts = ligne.split("\\|");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Format invalide, minimum 2 champs requis");
        }

        String type = parts[0].trim().toUpperCase();
        String nom  = parts[1].trim();

        
        // CULTURE|nom|x|y|r
        // ELEVAGE|nom|VOLAIL|x|y|r
        // AQUACOLE|nom|50|x|y|r

        switch (type) {

            case "CULTURE":
                if (parts.length < 5)
                    throw new IllegalArgumentException("CULTURE requiert : CULTURE|nom|x|y|r");
                Limite_geographique limC = parse_limites_obligatoires(parts, 2);
                new Zone_culture(nom, limC, ferme);
                return true;

            case "ELEVAGE":
                if (parts.length < 6)
                    throw new IllegalArgumentException("ELEVAGE requiert : ELEVAGE|nom|VOLAIL ou RUMINANT|x|y|r");
                type_elevage te = parse_type_elevage(parts[2].trim().toUpperCase());
                Limite_geographique limE = parse_limites_obligatoires(parts, 3);
                new Zone_elevage(nom, te, limE, ferme);
                return true;

            case "AQUACOLE":
                if (parts.length < 6)
                    throw new IllegalArgumentException("AQUACOLE requiert : AQUACOLE|nom|nombre_animaux|x|y|r");
                int nb = Integer.parseInt(parts[2].trim());
                Limite_geographique limA = parse_limites_obligatoires(parts, 3);
                new Zone_aquacole(nom, nb, limA, ferme);
                return true;

            default:
                throw new IllegalArgumentException("Type de zone inconnu : " + type);
        }
    }

    private type_elevage parse_type_elevage(String s) {
        switch (s) {
            case "VOLAIL":   return type_elevage.VOLAIL;
            case "RUMINANT": return type_elevage.RUMINANT;
            default: throw new IllegalArgumentException("Type elevage inconnu : " + s);
        }
    }

    private Limite_geographique parse_limites_obligatoires(String[] parts, int startIndex) {
        
        if (parts.length < startIndex + 3)
            throw new IllegalArgumentException("Limites manquantes : x|y|r");
        String sx = parts[startIndex].trim();
        String sy = parts[startIndex + 1].trim();
        String sr = parts[startIndex + 2].trim();
        if (sx.isEmpty() || sy.isEmpty() || sr.isEmpty())
            throw new IllegalArgumentException("Limites invalides : x|y|r");

        float x = Float.parseFloat(sx);
        float y = Float.parseFloat(sy);
        float r = Float.parseFloat(sr);
        return new Limite_geographique(r, x, y);
    }
}