import java.util.ArrayList;
import java.util.List;

public class Ferme {

    private List<Zone_geographique> liste_de_zones = new ArrayList<>();
    private List<Zone_culture> liste_de_zones_culture = new ArrayList<>();
    private List<Zone_elevage> liste_de_zones_elevage = new ArrayList<>();
    private List<Zone_aquacole> liste_de_zones_aquacole = new ArrayList<>();
    private List<Alerte> liste_alerte = new ArrayList<>();
    

    // appelees par les constructeurs des zones
    public void ajouter_zone(Zone_geographique zone) {
        liste_de_zones.add(zone);
    }
    public void ajouter_zone_culture(Zone_culture zone) {
        liste_de_zones_culture.add(zone);
    }
    public void ajouter_zone_elevage(Zone_elevage zone) {
        liste_de_zones_elevage.add(zone);
    }
    public void ajouter_zone_aquacole(Zone_aquacole zone) {
        liste_de_zones_aquacole.add(zone);
    }

    // chercher une zone par son nom
    public Zone_geographique chercher_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) return z;
        }
        return null;
    }

    // suspendre une zone par son nom
    public void suspension_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) {
                z.suspension();
                return;
            }
        }
        System.out.println("Zone '" + nom + "' introuvable.");
    }
    // activer une zone par son nom
    public void activer_zone(String nom) {
        for (Zone_geographique z : liste_de_zones) {
            if (z.get_nom().equalsIgnoreCase(nom)) {
                z.activation();
                return;
            }
        }
        System.out.println("Zone '" + nom + "' introuvable.");
    }
    //ajouter  alertes 
    public void ajouter_alerte(Alerte alerte) { liste_alerte.add(alerte); }
    
    // getters
    public List<Zone_geographique> get_liste_de_zones() { return liste_de_zones; }
    public List<Zone_culture> get_liste_de_zones_culture() { return liste_de_zones_culture; }
    public List<Zone_elevage> get_liste_de_zones_elevage() { return liste_de_zones_elevage; }
    public List<Zone_aquacole> get_liste_de_zones_aquacole() { return liste_de_zones_aquacole; }
    public List<Alerte> get_liste_alerte() { return liste_alerte; }
    
}
