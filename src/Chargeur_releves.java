import java.io.*;

public class Chargeur_releves {

    private Ferme ferme;

    public Chargeur_releves(Ferme ferme) {
        this.ferme = ferme;
    }

    public int charger_depuis_fichier(String fichier) {
        int releves_ok = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            int numLigne = 0;
            while ((ligne = reader.readLine()) != null) {
                numLigne++;
                ligne = ligne.trim();
                if (ligne.isEmpty() || ligne.startsWith("#")) continue;
                try {
                    boolean ok = traiter_ligne(ligne);
                    if (ok) releves_ok++;
                } catch (Exception e) {
                    System.out.println("Erreur ligne " + numLigne + " : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lecture : " + e.getMessage());
        }
        System.out.println(releves_ok + " relevé(s) chargé(s).");
        return releves_ok;
    }

    private boolean traiter_ligne(String ligne) throws Exception {
        String[] parts = ligne.split("\\|");
        String typeCap = parts[0].trim().toUpperCase();
        int idCapteur = Integer.parseInt(parts[1].trim());

        // Trouver le capteur par ID dans toutes les zones
        capteur c = trouverCapteurParId(idCapteur);
        if (c == null) throw new Exception("Capteur ID " + idCapteur + " introuvable");

        if (typeCap.equals("GPS")) {
            // GPS|ID|LONGITUDE|LATITUDE
            float lon = Float.parseFloat(parts[2].trim());
            float lat = Float.parseFloat(parts[3].trim());
            ((capteur_gps) c).setCoordonnee(lon, lat, ferme);
        } else {
            // SOL|ID|TYPE_MESURE|VALEUR
            TypeMesure m = TypeMesure.valueOf(parts[2].trim().toUpperCase());
            float val = Float.parseFloat(parts[3].trim());
            ((capteur_num) c).setVal(val, m, ferme);
        }
        return true;
    }

    private capteur trouverCapteurParId(int id) {
        for (Zone_geographique z : ferme.get_liste_de_zones()) {
            for (capteur c : z.getListCapteurs()) {
                if (c.getId() == id) return c;
            }
        }
        return null;
    }
}